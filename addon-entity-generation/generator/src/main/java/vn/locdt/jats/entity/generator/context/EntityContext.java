package vn.locdt.jats.entity.generator.context;

import vn.locdt.jats.module.modeling.model.Column;
import vn.locdt.jats.module.modeling.model.Relation;
import vn.locdt.jats.module.modeling.model.Table;
import vn.locdt.jats.util.common.OsUtils;

/**
 * Created by locdt on 2/9/2018.
 */
public class EntityContext extends JavaClassContext {

    public EntityContext(Table contextModel, String rootPackage, String projectPath, String outputName, String packageName) {
        super(contextModel, rootPackage, projectPath, outputName, packageName);
    }

    public EntityContext(Table contextModel) {
        super();
        this.contextModel = contextModel;
    }

    // Template actions
    public String generateFieldStatement(Column column, Relation relation) {
        String stt = "\t";
        if (relation == null)
            stt += "private " + importClass(column.getJavaType()) + " " + column.getJavaVarName();
        else {
            Table parentTable = relation.getParentColumn().getTable();
            stt += "private " + importClass(packageName + "." + parentTable.getJavaName()) + " " + parentTable.getJavaVarName();
        }
        return stt;
    }

    public String generateGetSetStatement(Column column, Relation relation) {
        String javaType;
        String variableName;

        if (relation != null) {
            Table parentTable = relation.getParentColumn().getTable();
            javaType = importClass(packageName + "." + parentTable.getJavaName());
            variableName = parentTable.getJavaVarName();
        }
        else {
            javaType = importClass(column.getJavaType());
            variableName = column.getJavaVarName();
        }

        StringBuilder s = new StringBuilder();
        s.append("\tpublic " + javaType + " get" + javaType + "() {").append(OsUtils.LINE_SEPARATOR);
        s.append("\t\treturn " + variableName + ";").append(OsUtils.LINE_SEPARATOR);
        s.append("\t}").append(OsUtils.LINE_SEPARATOR);
        s.append(OsUtils.LINE_SEPARATOR);
        s.append("\tpublic void set" + javaType)
            .append("(")
            .append(javaType + " " + variableName)
            .append("){")
            .append(OsUtils.LINE_SEPARATOR);
        s.append("\t\tthis." + variableName + " = " + variableName + ";").append(OsUtils.LINE_SEPARATOR);
        s.append("\t}");
        return s.toString();
    }

    public String generateRelationMappingAnnotationStatement(Column column, Relation relation) {
        StringBuilder s = new StringBuilder();

        // column is foreign key
        if (column.getTable().getName().equals(relation.getChildTableName())) {
            importClass("javax.persistence.ManyToOne");
            importClass("javax.persistence.JoinColumn");
            s.append("    @ManyToOne(fetch = FetchType.EAGER)").append(OsUtils.LINE_SEPARATOR);
            s.append("    @JoinColumn(name = \"" + relation.getChildColumnName() + "\")");
        }
        else if (column.getTable().getName().equals(relation.getParentTableName())) {
            importClass("javax.persistence.OneToMany");
            importClass("javax.persistence.CascadeType");
            s.append("@OneToMany(\n" +
                    "        cascade = CascadeType.ALL, \n" +
                    "        orphanRemoval = true\n" +
                    "    )");
        }
        return s.toString();
    }

    @Override
    public String getClassName() {
        return contextModel.getJavaName();
    }

    @Override
    public String getExtendStatement() {
        return "";
    }

    @Override
    public String getImplementStatement() {
        return "";
    }
}

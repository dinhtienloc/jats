${context.packageStatement}

<#assign classStatement>
${context.importClassQuietly("java.io.Serializable")}
${context.importClassQuietly("java.util.List")}

public interface IBaseDao<E, K extends Serializable> {
	List<E> findAll();
	void save(E entity);
	void update(E entity) ;
	void delete(E entity);
	void deleteById(K id);
	E findById(K id);
}
</#assign>
${context.getImports()}

${classStatement}


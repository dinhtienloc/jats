package vn.locdt.jats.synergix.addon.db;

import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class DatabaseGroup {
    private String name;
    private Set<DatabaseInfo> dbInfos;

    public DatabaseGroup(String name) {
        this.name = name;
        this.dbInfos = new HashSet<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<DatabaseInfo> getDbInfos() {
        return this.dbInfos;
    }

    public void setDbInfos(Set<DatabaseInfo> dbInfos) {
        this.dbInfos = dbInfos;
    }

    public void addDatabaseInfo(DatabaseInfo info) {
        if(info != null) this.dbInfos.add(info);
    }

	@Override
	public String toString() {
		return "- " + this.name + ": " + StringUtils.join(this.dbInfos.stream()
						.map(info -> info.toString() + ("ctrl".equalsIgnoreCase(info.category) ? " (ctrl)" : ""))
						.collect(Collectors.toList()), ", ");
	}
}

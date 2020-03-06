package com.example.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author zhaolei
 * Create: 2020/2/25 12:12
 * Modified By:
 * Description: 功能表
 */
@Entity
@Table(name = "permission_info")
@DynamicUpdate
@DynamicInsert
public class PermissionInfo implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="name", length = 256, columnDefinition = "comment '权限名称'")
	private String name;
	
	@Column(name="url")
	private String url;
	
	@Column(name="code")
	private String code;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", insertable = false, updatable = false, foreignKey = @ForeignKey(foreignKeyDefinition = "none", value = ConstraintMode.NO_CONSTRAINT))
	@NotFound(action = NotFoundAction.IGNORE)
	private PermissionInfo parentModel;

	@JsonBackReference
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(
			name = "role_permission_rel",
			joinColumns = {@JoinColumn(name = "permission_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	private Set<RoleInfo> roleInfos;

	public PermissionInfo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public PermissionInfo getParentModel() {
		return parentModel;
	}

	public void setParentModel(PermissionInfo parentModel) {
		this.parentModel = parentModel;
	}

	public Set<RoleInfo> getRoleInfos() {
		return roleInfos;
	}

	public void setRoleInfos(Set<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
	}

	@Override
	public String toString() {
		return "PermissionInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", url='" + url + '\'' +
				", code='" + code + '\'' +
				", parentModel=" + parentModel +
				'}';
	}
}


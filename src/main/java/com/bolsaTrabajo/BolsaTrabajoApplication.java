package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.RoleService;
import com.bolsaTrabajo.service.PermissionService;
import com.bolsaTrabajo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;
import java.util.*;

@SpringBootApplication
public class BolsaTrabajoApplication implements CommandLineRunner {

	@Autowired
	private SkillCategoryRepository skillCategoryRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	PermissionService permissionService;

	public static void main(String[] args) {
		SpringApplication.run(BolsaTrabajoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception{

		//Permisos, roles y usuario inicial en la aplicacion


		Role role = new Role("POSTULANTE");
		Role role2 = new Role("EMPRESA");

		if(roleService.count()<=1){
			roleService.save(role);
			roleService.save(role2);
		}

		UserService userService;

		if(roleService.findByName("ADMIN")==null) {
			Permission ucpermission = createPermissionIfNotExist("USER.CREATE");
			Permission urpermission = createPermissionIfNotExist("USER.READ");
			Permission uepermission = createPermissionIfNotExist("USER.EDIT");
			Permission udpermission = createPermissionIfNotExist("USER.DELETE");

			Set<Permission> adminPermission = new HashSet<>(Arrays.asList(ucpermission, urpermission, uepermission, udpermission));
			createRoleIfNotFound("ADMIN", adminPermission);
		}

		SkillCategory category1 = new SkillCategory(
				"Locomotrices","HLOC"
		);

		SkillCategory category2 = new SkillCategory(
				"Intelectuales","HINT"
		);

		SkillCategory category3 = new SkillCategory(
				"Sociales","HSOC"
		);

		SkillCategory category4 = new SkillCategory(
				"Gerenciales","HGER"
		);
		SkillCategory category5 = new SkillCategory(
				"Linguisticas","HLIN"
		);
		if (skillCategoryRepository.count()<0){
			skillCategoryRepository.save(category1);
			skillCategoryRepository.save(category2);
			skillCategoryRepository.save(category3);
			skillCategoryRepository.save(category4);
		}


	}

	@Transactional
	private Permission createPermissionIfNotExist(String name){
		Permission permission = permissionService.findByName(name);
		if (permission == null){
			permission = new Permission(name);
			permissionService.save(permission);
		}
		return permission;
	}

	@Transactional
	private Role createRoleIfNotFound(String name, Set<Permission> permissions){
		Role role = roleService.findByName(name);
		if (role==null){
			role = new Role(name);
			role.setPermissions(permissions);
			roleService.save(role);
		}
		return role;
	}
}

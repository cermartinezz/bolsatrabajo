package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.RoleService;
import com.bolsaTrabajo.service.SkillCategoryService;
import com.bolsaTrabajo.service.SkillService;
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
	private SkillCategoryService skillCategoryService;

	@Autowired
	private RoleService roleService;

	@Autowired
	PermissionService permissionService;

	@Autowired
	private SkillService skillService;

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
		Skill skill = new Skill("Nose 1", "nose1", category1);
		Skill skill2 = new Skill("Nose 2", "nose2", category2);
		Skill skill3 = new Skill("Nose 3", "nose3", category3);
		Skill skill4 = new Skill("Nose 4", "nose4", category4);
		Skill skill5 = new Skill("Nose algo", "nose_2", category1);

		if (skillCategoryService.count()<=0){
			skillCategoryService.save(category1);
			skillCategoryService.save(category2);
			skillCategoryService.save(category3);
			skillCategoryService.save(category4);
			skillService.storeSkill(skill);
			skillService.storeSkill(skill2);
			skillService.storeSkill(skill3);
			skillService.storeSkill(skill4);
			skillService.storeSkill(skill5);
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

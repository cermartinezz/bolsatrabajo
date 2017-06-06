package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Language;
import com.bolsaTrabajo.model.LanguageLevel;
import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.model.Permission;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.*;
import com.bolsaTrabajo.service.SkillCategoryService;
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

	@Autowired
	private LanguageService languageService;

	@Autowired
	private LanguageLevelService languageLevelService;

	public static void main(String[] args) {
		SpringApplication.run(BolsaTrabajoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception {


		if (languageService.count() <=0) {
			insertLanguages();
		}

		if (languageLevelService.count() <=0) {
			insertLanguageLevels();
		}

		//Permisos, roles y usuario inicial en la aplicacion


		Role role = new Role("POSTULANTE");
		Role role2 = new Role("EMPRESA");

		if (roleService.count() <= 1) {
			roleService.save(role);
			roleService.save(role2);
		}

		UserService userService;

		if (roleService.findByName("ADMIN") == null) {
			Permission ucpermission = createPermissionIfNotExist("USER.CREATE");
			Permission urpermission = createPermissionIfNotExist("USER.READ");
			Permission uepermission = createPermissionIfNotExist("USER.EDIT");
			Permission udpermission = createPermissionIfNotExist("USER.DELETE");

			Set<Permission> adminPermission = new HashSet<>(Arrays.asList(ucpermission, urpermission, uepermission, udpermission));
			createRoleIfNotFound("ADMIN", adminPermission);
		}

		SkillCategory category1 = new SkillCategory(
				"Locomotrices", "HLOC"
		);

		SkillCategory category2 = new SkillCategory(
				"Intelectuales", "HINT"
		);

		SkillCategory category3 = new SkillCategory(
				"Sociales", "HSOC"
		);

		SkillCategory category4 = new SkillCategory(
				"Gerenciales", "HGER"
		);
		SkillCategory category5 = new SkillCategory(
				"Linguisticas", "HLIN"
		);
		Skill skill = new Skill("Nose 1", "nose1", category1);
		Skill skill2 = new Skill("Nose 2", "nose2", category2);
		Skill skill3 = new Skill("Nose 3", "nose3", category3);
		Skill skill4 = new Skill("Nose 4", "nose4", category4);
		Skill skill5 = new Skill("Nose algo", "nose_2", category1);

		if (skillCategoryService.count() <= 0) {
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

	private void insertLanguageLevels() {
		LanguageLevel lvl1 = new LanguageLevel(
			"Basico","A1"
		);
		LanguageLevel lvl2 = new LanguageLevel(
				"Elemental","A2"
		);
		LanguageLevel lvl3 = new LanguageLevel(
				"Pre-Intermedio","B1"
		);
		LanguageLevel lvl4 = new LanguageLevel(
				"Intermedio Superior","B2"
		);
		LanguageLevel lvl5 = new LanguageLevel(
				"Avanzado","C1"
		);
		LanguageLevel lvl6 = new LanguageLevel(
				"Superior","C2"
		);
		languageLevelService.store(lvl1);
		languageLevelService.store(lvl2);
		languageLevelService.store(lvl3);
		languageLevelService.store(lvl4);
		languageLevelService.store(lvl5);
		languageLevelService.store(lvl6);

	}

	private void insertLanguages(){
		Language language1 = new Language(
				"ES","Español"
		);

		Language language2 = new Language(
				"EN","Inglés"
		);

		Language language3 = new Language(
				"IT","Italiano"
		);

		Language language4 = new Language(
				"DE","Alemán"
		);

		Language language5 = new Language(
				"JA","Japonés"
		);

		languageService.store(language1);
		languageService.store(language2);
		languageService.store(language3);
		languageService.store(language4);
		languageService.store(language5);



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

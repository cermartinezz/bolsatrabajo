package com.bolsaTrabajo;

import com.bolsaTrabajo.model.Role;
import com.bolsaTrabajo.model.catalog.Skill;
import com.bolsaTrabajo.model.catalog.SkillCategory;
import com.bolsaTrabajo.repositories.SkillCategoryRepository;
import com.bolsaTrabajo.service.RoleService;
import com.bolsaTrabajo.service.SkillCategoryService;
import com.bolsaTrabajo.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.Transactional;

@SpringBootApplication
public class BolsaTrabajoApplication implements CommandLineRunner {

	@Autowired
	private SkillCategoryService skillCategoryService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private SkillService skillService;

	public static void main(String[] args) {
		SpringApplication.run(BolsaTrabajoApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception{

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


		Role role = new Role("POSTULANTE");
		Role role2 = new Role("EMPRESA");

		if(roleService.count()<=0){
			roleService.save(role);
			roleService.save(role2);
		}

	}
}

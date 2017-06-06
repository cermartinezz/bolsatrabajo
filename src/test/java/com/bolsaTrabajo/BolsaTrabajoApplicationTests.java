package com.bolsaTrabajo;

import com.bolsaTrabajo.model.catalog.Certification;
import com.bolsaTrabajo.model.catalog.Institution;
import com.bolsaTrabajo.service.CertificationService;
import com.bolsaTrabajo.service.InstitutionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BolsaTrabajoApplicationTests {


	@Autowired
	private
	InstitutionService institutionService;

	@Autowired
	CertificationService certificationService;

	@Test
	public void newCertification() {

		Institution ues = institutionService.findInstitutionByCode("UES");
		Certification certification = new Certification();
		certification.setCertificationTitle("IOS DEVELOPER");
		certification.setInstitution(ues);

		certificationService.storeCertification(certification);

	}

}

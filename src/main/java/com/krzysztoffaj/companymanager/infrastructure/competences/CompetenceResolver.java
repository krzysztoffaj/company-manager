package com.krzysztoffaj.companymanager.infrastructure.competences;

import com.krzysztoffaj.companymanager.models.Employee;

public class CompetenceResolver {
    public Competence getCompetence(Employee employee) {
        switch (employee.getPosition()) {
            case PM:
                return new PmCompetence();
            case PO:
                return new PoCompetence();
            case Scrummaster:
                return new ScrummasterCompetence();
            case Developer:
                return new DeveloperCompetence();
            case DevOps:
                return new DevOpsCompetence();
            case Tester:
                return new TesterCompetence();
            case Analyst:
                return new AnalystCompetence();
            default:
                return null;
        }
    }
}

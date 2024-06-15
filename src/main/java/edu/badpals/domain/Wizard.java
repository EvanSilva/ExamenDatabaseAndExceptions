package edu.badpals.domain;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table (name = "t_wizards")
public class Wizard extends PanacheEntityBase {

    @Id
    @Column (name = "wizard_name")
    private String name = "";

    @Column (name = "wizard_dexterity")
    private int desterity = 0;

    @Column (name = "wizard_person")
    @Enumerated(EnumType.STRING)
    private WizardPersona wizard = null;

    public Wizard() {
    }

    public Wizard(String name, int desterity, WizardPersona wizard) {
        this.name = name;
        this.desterity = desterity;
        this.wizard = wizard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDesterity() {
        return desterity;
    }

    public void setDesterity(int desterity) {
        this.desterity = desterity;
    }

    public WizardPersona getWizard() {
        return wizard;
    }

    public void setWizard(WizardPersona wizard) {
        this.wizard = wizard;
    }

    @Override
    public String toString() {
        return "Wizard{" +
                "name='" + name + '\'' +
                ", desterity=" + desterity +
                ", wizard=" + wizard +
                '}';
    }
}

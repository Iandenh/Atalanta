package nl.fa5t.test.app.Repository;

import nl.fa5t.test.app.Model.Agenda;

/**
 * Created by ian on 14-9-16.
 */
public class AgendasRepository extends Repository<Agenda> {
    public AgendasRepository() {
        super();
        model = Agenda.class;
        singular = "agenda";
    }
}

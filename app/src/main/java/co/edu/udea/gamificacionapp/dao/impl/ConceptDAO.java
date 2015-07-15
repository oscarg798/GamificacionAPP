package co.edu.udea.gamificacionapp.dao.impl;

import org.json.JSONArray;

import java.util.List;

import co.edu.udea.gamificacionapp.R;
import co.edu.udea.gamificacionapp.controllers.abstracts.AbstractController;
import co.edu.udea.gamificacionapp.dao.abstracts.AbstractDao;
import co.edu.udea.gamificacionapp.dao.interfaces.IConceptDAO;
import co.edu.udea.gamificacionapp.entities.core.Concept;
import co.edu.udea.gamificacionapp.entities.utils.CouplePostParam;
import co.edu.udea.gamificacionapp.factories.impl.ConceptFactory;
import co.edu.udea.gamificacionapp.services.http.PostServices;

/**
 * Created by oscargallon on 3/06/15.
 */
public class ConceptDAO extends AbstractDao implements IConceptDAO {
    public ConceptDAO(AbstractController abstractController) {
        super(abstractController);
    }

    @Override
    public void getConcepts(List<CouplePostParam> couplePostParamList) {
        PostServices postServices = PostServices.getInsance(this, couplePostParamList);
        postServices.execute(getAbstractController().getActivity()
                .getString(R.string.base_url) + getAbstractController().getActivity()
                .getString(R.string.base_url));
    }

    @Override
    public void proccessRestFulResponse(String stringResp) {
        super.proccessRestFulResponse(stringResp);
    }

    @Override
    public void processJsonArrayResponse(JSONArray jsonArray) {
        super.processJsonArrayResponse(jsonArray);
        List<Concept> conceptList = ConceptFactory.getInstance().getConceptsFromJsonArray(
                jsonArray, getAbstractController().getActivity().getApplicationContext());
        if(conceptList==null) {
            showErrorMessage("error", "error en factory");
            return;
        }
    }
}

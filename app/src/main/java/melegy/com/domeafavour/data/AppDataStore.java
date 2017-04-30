package melegy.com.domeafavour.data;

import java.util.List;

import melegy.com.domeafavour.data.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/27/17.
 */

public interface AppDataStore {

    Observable<List<Favor>> getFavors();
}

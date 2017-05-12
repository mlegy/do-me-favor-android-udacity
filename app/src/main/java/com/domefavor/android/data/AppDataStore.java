package com.domefavor.android.data;

import java.util.List;

import com.domefavor.android.data.models.resources.Favor;
import rx.Observable;

/**
 * Created by ahmad on 4/27/17.
 */

public interface AppDataStore {

    Observable<List<Favor>> getFavors(double x, double y);
}

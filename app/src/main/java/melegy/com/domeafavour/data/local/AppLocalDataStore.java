
package melegy.com.domeafavour.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pushtorefresh.storio.contentresolver.ContentResolverTypeMapping;
import com.pushtorefresh.storio.contentresolver.StorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.impl.DefaultStorIOContentResolver;
import com.pushtorefresh.storio.contentresolver.queries.DeleteQuery;
import com.pushtorefresh.storio.contentresolver.queries.Query;

import java.util.List;

import javax.inject.Inject;

import melegy.com.domeafavour.data.AppDataStore;
import melegy.com.domeafavour.data.models.resources.Favor;
import melegy.com.domeafavour.data.models.resources.FavorStorIOContentResolverDeleteResolver;
import melegy.com.domeafavour.data.models.resources.FavorStorIOContentResolverGetResolver;
import melegy.com.domeafavour.data.models.resources.FavorStorIOContentResolverPutResolver;
import rx.Observable;

/**
 * Created by ahmad on 4/28/17.
 */

public class AppLocalDataStore implements AppDataStore {

    private StorIOContentResolver mStorIOContentResolver;

    @Inject
    public AppLocalDataStore(@NonNull Context context) {
        mStorIOContentResolver = DefaultStorIOContentResolver.builder()
                .contentResolver(context.getContentResolver())
                .addTypeMapping(Favor.class, ContentResolverTypeMapping.<Favor> builder()
                        .putResolver(new FavorStorIOContentResolverPutResolver())
                        .getResolver(new FavorStorIOContentResolverGetResolver())
                        .deleteResolver(new FavorStorIOContentResolverDeleteResolver())
                        .build())
                .build();
    }

    @Override
    public Observable<List<Favor>> getFavors(double x, double y) {
        return mStorIOContentResolver.get()
                .listOfObjects(Favor.class)
                .withQuery(Query.builder().uri(DatabaseContract.Favor.CONTENT_URI).build())
                .prepare()
                .asRxObservable();
    }

    public void performLocalStorage(List<Favor> favors) {
        deleteFavorsFromDatabase().subscribe(deleteResult -> saveFavorsToDatabase(favors));
    }

    private Observable<com.pushtorefresh.storio.contentresolver.operations.delete.DeleteResult> deleteFavorsFromDatabase() {
        return mStorIOContentResolver.delete()
                .byQuery(DeleteQuery.builder().uri(DatabaseContract.Favor.CONTENT_URI).build())
                .prepare().asRxObservable();
    }

    private void saveFavorsToDatabase(List<Favor> favors) {
        mStorIOContentResolver.put().objects(favors).prepare().asRxObservable().subscribe();
    }

}

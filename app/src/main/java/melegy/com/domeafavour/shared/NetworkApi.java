
package melegy.com.domeafavour.shared;

import java.util.List;

import melegy.com.domeafavour.shared.models.resources.Favor;
import melegy.com.domeafavour.shared.models.resources.User;
import melegy.com.domeafavour.shared.models.responses.AddFavorResponse;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ahmad on 5/9/17.
 */

public interface NetworkApi {

    @POST("/users")
    Observable<User> addUser(@Body User user);

    @GET("/users/{id}")
    Observable<User> getUserById(@Path("id") String userID);

    @PATCH("/users/{id}")
    Observable<User> updateUser(@Path("id") String userID);

    @POST("/favors")
    Observable<AddFavorResponse> addFavor(@Body Favor favor);

    @PATCH("/favors/{id}/benefactor")
    Observable<Favor> addBenefactorToFavor(@Path("id") String userId);

    @PATCH("/favors/{id}/done")
    Observable<Favor> markFavorAsDone(@Path("id") String favorID);

    @GET("/favors")
    Observable<List<Favor>> getNearbyFavors(@Query("long") long x, @Query("lat") long y);


}

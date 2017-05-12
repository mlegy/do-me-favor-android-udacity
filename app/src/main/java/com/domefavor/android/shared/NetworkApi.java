
package com.domefavor.android.shared;

import com.domefavor.android.data.models.requests.AddFavorRequest;
import com.domefavor.android.data.models.requests.SignUpRequest;
import com.domefavor.android.data.models.resources.Favor;
import com.domefavor.android.data.models.resources.User;
import com.domefavor.android.data.models.responses.AddFavorResponse;

import java.util.List;

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
    Observable<User> addUser(@Body SignUpRequest user);

    @GET("/users/{id}")
    Observable<User> getUserById(@Path("id") String userID);

    @PATCH("/users/{id}")
    Observable<User> updateUser(@Path("id") String userID);

    @POST("/favors")
    Observable<AddFavorResponse> addFavor(@Body AddFavorRequest favor);

    @PATCH("/favors/{id}/benefactor")
    Observable<Favor> addBenefactorToFavor(@Path("id") String userId);

    @PATCH("/favors/{id}/done")
    Observable<Favor> markFavorAsDone(@Path("id") String favorID);

    @GET("/favors")
    Observable<List<Favor>> getNearbyFavors(@Query("long") double x, @Query("lat") double y);


}


package melegy.com.domeafavour.shared.di.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import melegy.com.domeafavour.BuildConfig;
import melegy.com.domeafavour.shared.NetworkApi;
import melegy.com.domeafavour.shared.models.AutoValueGsonFactory;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.Retrofit.Builder;

/**
 * Created by ahmad on 5/9/17.
 */

@Module
public class NetModule {

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().registerTypeAdapterFactory(AutoValueGsonFactory.create())
                .create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder().addNetworkInterceptor(chain -> {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder()
                    .addQueryParameter("access_token", BuildConfig.ACCESS_TOKEN)
                    .build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }).build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.API_URL)
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    NetworkApi providesNetworkApi(Retrofit retrofit){
        return retrofit.create(NetworkApi.class);
    }
}

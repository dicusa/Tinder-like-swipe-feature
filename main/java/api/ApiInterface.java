package api;

import modelclass.Student;
import modelclass.Student_;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiInterface {

    @POST("/student/.json")
    Call<Student> setData(@Body Student_ student);

    @GET("/.json")
    Call<Student> getData();

    @PUT("/student/.json")
    Call<Student> setDataWithoutRandomness( @Body Student_ student);
}

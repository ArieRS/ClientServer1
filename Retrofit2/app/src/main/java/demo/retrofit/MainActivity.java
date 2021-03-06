package demo.retrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

import demo.retrofit.Rest.ApiClient;
import demo.retrofit.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btGet, btUpdate, btInsert, btDelete;
    ApiInterface mApiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btGet = (Button) findViewById(R.id.btGet);
        btUpdate = (Button) findViewById(R.id.btUpdate);
        btInsert = (Button) findViewById(R.id.btInsert);
        btDelete = (Button) findViewById(R.id.btDelete);

        mApiInterface  = ApiClient.getClient().create(ApiInterface.class);

        btGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<GetPembelian> pembelianCall = mApiInterface.getPembelian();
                pembelianCall.enqueue(new Callback<GetPembelian>() {
                    @Override
                    public void onResponse(Call<GetPembelian> call, Response<GetPembelian> response) {
                        List<Pembelian> pembelianList = response.body().getListDataPembelien();
                        Log.d("Retrofit Get", "Jumlah data pembelian: " + String.valueOf(pembelianList.size()));
                    }

                    @Override
                    public void onFailure(Call<GetPembelian> call, Throwable t) {
                        // Log error
                        Log.e("Retrofit Get", t.toString());
                    }
                });
            }
        });

        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelPembelian> updatePembelianCall = mApiInterface.putPembelian("beli16","pemb11","2016-10-23","500000","tiket11");
                updatePembelianCall.enqueue(new Callback<PostPutDelPembelian>() {
                    @Override
                    public void onResponse(Call<PostPutDelPembelian> call, Response<PostPutDelPembelian> response) {
                        Log.d("Retrofit Update", "Status Update: " + String.valueOf(response.body().status));
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPembelian> call, Throwable t) {
                        Log.d("Retrofit Update", t.getMessage());
                    }
                });
            }
        });

        btInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelPembelian> postPembelianCall = mApiInterface.postPembelian("beli16","pemb11","2016-10-23","500000","tiket11");
                postPembelianCall.enqueue(new Callback<PostPutDelPembelian>() {
                    @Override
                    public void onResponse(Call<PostPutDelPembelian> call, Response<PostPutDelPembelian> response) {
                        Log.d("Retrofit Insert", "Status Insert: " + String.valueOf(response.body().status));
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPembelian> call, Throwable t) {
                        Log.d("Retrofit Insert", t.getMessage());
                    }
                });
            }
        });

        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<PostPutDelPembelian> deletePembelian = mApiInterface.deletePembelian("beli16");
                deletePembelian.enqueue(new Callback<PostPutDelPembelian>() {
                    @Override
                    public void onResponse(Call<PostPutDelPembelian> call, Response<PostPutDelPembelian> response) {
                        Log.i("Retrofit Delete", "Status Delete: " + String.valueOf(response.body().status));
                    }

                    @Override
                    public void onFailure(Call<PostPutDelPembelian> call, Throwable t) {
                        Log.i("Retrofit Delete", t.getMessage());
                    }
                });
            }
        });
    }
}

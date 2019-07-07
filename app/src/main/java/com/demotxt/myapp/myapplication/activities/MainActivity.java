package com.demotxt.myapp.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.demotxt.myapp.myapplication.R;
import com.demotxt.myapp.myapplication.adapters.RecyclerViewAdapter;
import com.demotxt.myapp.myapplication.model.Anime;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String JSON_URL = "https://gist.githubusercontent.com/aws1994/f583d54e5af8e56173492d3f60dd5ebf/raw/c7796ba51d5a0d37fc756cf0fd14e54434c547bc/anime.json" ;
    private JsonArrayRequest request ;
    private RequestQueue requestQueue ;
    private List<Anime> lstAnime ;
    private RecyclerView recyclerView ;
     static int j=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstAnime = new ArrayList<>() ;
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();



    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject  = null ;

                for (int i = 0 ; i < response.length(); i++ ) {


                    try {
                        jsonObject = response.getJSONObject(i) ;
                        Anime anime = new Anime() ;
                        anime.setName(jsonObject.getString("name"));
                        anime.setDescription(jsonObject.getString("description"));
                        anime.setRating(jsonObject.getString("Rating"));
                        anime.setCategorie(jsonObject.getString("categorie"));
                        anime.setNb_episode(jsonObject.getInt("episode"));
                        anime.setStudio(jsonObject.getString("studio"));
                        switch (i) {
                                case 0:
                                    anime.setImage_url("https://images-na.ssl-images-amazon.com/images/I/51XuAYoo-rL.jpg");
                                    break;
                                case 1:
                                    anime.setImage_url("https://images-na.ssl-images-amazon.com/images/I/91f-nl2S01L._SY606_.jpg");
                                    break;
                                case 2:
                                    anime.setImage_url("https://images-na.ssl-images-amazon.com/images/I/618khoeeNbL._SY679_.jpg");
                                    break;
                                case 3:
                                    anime.setImage_url("https://i.pinimg.com/originals/ed/cc/cd/edcccde17ade44fbe36571470ef86720.jpg");
                                    break;
                                case 4:
                                    anime.setImage_url("https://images-na.ssl-images-amazon.com/images/I/51SWDXRc2sL.jpg");
                                    break;
                                case 5:
                                    anime.setImage_url("https://i.pinimg.com/originals/7a/c4/f0/7ac4f0cecc24cea68f1d77b41adbd515.jpg");
                                    break;
                                case 6:
                                    anime.setImage_url("https://images-na.ssl-images-amazon.com/images/I/51UfgHEbgiL.jpg");
                                    break;
                                case 7:
                                    anime.setImage_url("https://images-na.ssl-images-amazon.com/images/I/61Omvy8ZHgL.jpg");
                                    break;
                                case 8:
                                    anime.setImage_url("https://images-na.ssl-images-amazon.com/images/I/71IkcdOWX7L._SY679_.jpg");
                                    break;
                                case 9:
                                    anime.setImage_url("https://i.ebayimg.com/images/g/Fv8AAOSwLVFblq8t/s-l300.jpg");
                                    break;
                                case 10:
                                    anime.setImage_url("https://cdn.shopify.com/s/files/1/0747/3829/products/mz1238_1024x1024.jpeg?v=1485014979");
                                    break;
                                case 11:
                                    anime.setImage_url("https://render.fineartamerica.com/images/rendered/default/poster/8/10/break/images/artworkimages/medium/1/hunter-x-hunter-ahinta-mubasiroh.jpg");
                                    break;
                                default:
                                    anime.setImage_url("img");
                                    break;
                            }
                        lstAnime.add(anime);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setuprecyclerview(lstAnime);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request) ;


    }

    private void setuprecyclerview(List<Anime> lstAnime) {


        RecyclerViewAdapter myadapter = new RecyclerViewAdapter(this,lstAnime) ;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(myadapter);

    }
}

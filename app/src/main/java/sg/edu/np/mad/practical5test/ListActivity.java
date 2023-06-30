package sg.edu.np.mad.practical5test;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity{
    RecyclerView recyclerView;
    ArrayList<User> userList;
    DBHandler db;
    myAdapter adapter;
    TextView username, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        db = new DBHandler(this, null, null, 1);
        userList = db.getUsers();
        recyclerView = findViewById(R.id.recyclerView);
        adapter = new myAdapter(userList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
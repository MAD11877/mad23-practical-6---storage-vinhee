package sg.edu.np.mad.practical5test;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    final String TAG = "Main Activity";
    private boolean following = false;
    private Button followButton;
    private Button msgButton;
    private RecyclerView recyclerView;
    private ArrayList<User> userList;

    TextView Username;
    TextView Description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG, "On Create!");

        DBHandler db = new DBHandler(this, null, null, 1);

        followButton = findViewById(R.id.followed);
        Username = findViewById(R.id.userName);
        Description = findViewById(R.id.Desc);
        msgButton = findViewById(R.id.message);

        Intent i = getIntent();
        int position = i.getIntExtra("position", -1);
        boolean followed = i.getBooleanExtra("followed", false);
        Log.d(TAG, "Followed: " + followed);
        ArrayList<User> userList = (ArrayList<User>) i.getSerializableExtra("arrayList");
        User user = userList.get(position);

        Username.setText(user.getName());
        Description.setText(user.getDescription());
        if (followed == true){
            followButton.setText("Unfollow");
        }
        else {
            followButton.setText("Follow");
        }
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.followed == false){
                    user.followed = true;
                    Log.d(TAG, "User ID: " + user.getId() + ", Followed: " + user.isFollowed());
                    db.updateUser(user);
                    followButton.setText("Unfollow");
                    Toast.makeText(getApplicationContext(), "Followed", Toast.LENGTH_LONG).show();
                }
                else{
                    user.followed = false;
                    Log.d(TAG, "User ID: " + user.getId() + ", Followed: " + user.isFollowed());
                    db.updateUser(user);
                    followButton.setText("Follow");
                    Toast.makeText(getApplicationContext(), "Unfollowed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
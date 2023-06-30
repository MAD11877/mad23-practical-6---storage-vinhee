package sg.edu.np.mad.practical5test;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class myViewHolder extends RecyclerView.ViewHolder{
    TextView username;
    TextView desc;
    ImageView pfp;


    public myViewHolder(View itemView){
        super(itemView);
        username = itemView.findViewById(R.id.ProfileName);
        desc = itemView.findViewById(R.id.Description);
        pfp = itemView.findViewById(R.id.ProfileImg);
    }
}


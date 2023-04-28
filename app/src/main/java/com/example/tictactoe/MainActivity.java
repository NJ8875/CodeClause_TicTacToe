package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    int active_player=0;
    boolean game_active=true;
//    game states
//    0=X
//    1=O
//    2=null

    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winPositions={{0,1,2} ,{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}};



   public void onTap(View view){
       ImageView img = (ImageView)view;
       int tappedIamge=Integer.parseInt(img.getTag().toString());
       //creating a game reset option
       Button btn=findViewById(R.id.button);
       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               reset(view);
           }
       });
       //creating a game reset functionality
       if(!game_active){
           reset(view);
       }
       if(gamestate[tappedIamge]==2) {
           gamestate[tappedIamge] = active_player;
           img.setTranslationY(-1000f);
           if (active_player == 0 ) {
               active_player = 1;
               img.setImageResource(R.drawable.x);
               TextView status=findViewById(R.id.status);
               status.setText("O's turn - Tap to play ");
           } else {
               active_player = 0;
               img.setImageResource(R.drawable.o);
               TextView status=findViewById(R.id.status);
               status.setText("X's turn - Tap to play ");

           }

           img.animate().translationYBy(1000f).setDuration(300);
       }

       //check if any player win the game or not
       // Check if any player has won
       for(int[] winPosition: winPositions) {
           if (gamestate[winPosition[0]] == gamestate[winPosition[1]] &&
                   gamestate[winPosition[1]] == gamestate[winPosition[2]] &&
                   gamestate[winPosition[0]] != 2) {
               // Somebody has won! - Find out who!
               String winnerStr;
               game_active = false;
               if (gamestate[winPosition[0]] == 0) {
                   winnerStr = "X has won";
               } else {
                   winnerStr = "O has won";
               }
               // Update the status bar for winner announcement
               TextView status = findViewById(R.id.status);
               status.setText(winnerStr);

           }
       }


   }

    public void reset(View view){
        game_active=true;
        active_player=0;
        for(int i=0; i<gamestate.length; i++){
            gamestate[i] = 2;
        }
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        TextView status = findViewById(R.id.status);
        status.setText("X's Turn - Tap to play");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
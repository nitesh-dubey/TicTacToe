package com.nitesh.dubey.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn1,btn2;
    EditText edttxt1,edttxt2;
    TextView txt2;
    String player1,player2;
    boolean isGameOver = false;
    GridLayout gridLayout;


    enum Player {
        ONE,TWO,NULL
    }



    Player currentPlayer = Player.ONE;
    int [][] winningCases = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    Player[] scoreBoard = new Player[9];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.credentials);
        btn1 = findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edttxt1 = findViewById(R.id.edttxt1);
                edttxt2 = findViewById(R.id.edttxt2);
                player1 = edttxt1.getText().toString();
                player2 = edttxt2.getText().toString();
                setContentView(R.layout.activity_main);
                gridLayout = findViewById(R.id.gridLayout);
                btn2 = findViewById(R.id.btn2);
                resetScoreBoard();
            }
        });


      /*  btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isGameOver = false;
                currentPlayer = Player.ONE;
                resetGame();

            }
        });

      */

    }



    public void tappedImageView (View imageViewTapped) {

        ImageView imageView = (ImageView) imageViewTapped;
        int x = Integer.parseInt(imageView.getTag().toString());
        if((scoreBoard[x] != Player.NULL) || isGameOver) return;


        scoreBoard[x] = currentPlayer;

        if(currentPlayer == Player.ONE){
            imageView.setTranslationX(-1000f);
            imageView.setImageResource(R.drawable.lion);
            imageView.animate().translationXBy(1000f).rotationBy(36000).alpha(1).setDuration(1000);
            currentPlayer = Player.TWO;
        }else if(currentPlayer == Player.TWO) {
            imageView.setTranslationX(1000f);
            imageView.setImageResource(R.drawable.tiger);
            imageView.animate().translationXBy(-1000f).rotationBy(-36000).alpha(1).setDuration(1000);
            currentPlayer = Player.ONE;
        }

        //Check For Winner



        for(int i=0;i<winningCases.length;i++){
            if((scoreBoard[winningCases[i][0]] == scoreBoard[winningCases[i][1]]) &&
                    (scoreBoard[winningCases[i][1]] == scoreBoard[winningCases[i][2]]) && (scoreBoard[winningCases[i][0]] != Player.NULL)) {
                isGameOver = true;
                break;

            }
        }

        if(isGameOver) {
            if(currentPlayer == Player.TWO) {
                Toast.makeText(this,player1 +" Wins!!",Toast.LENGTH_LONG ).show();
            }else if(currentPlayer == Player.ONE) {
                Toast.makeText(this,player2 +" Wins!!",Toast.LENGTH_LONG).show();
            }
            btn2.setVisibility(View.VISIBLE);
        }


        if(checkForDraw()){
            Toast.makeText(this,"Game Draw",Toast.LENGTH_LONG).show();
            btn2.setVisibility(View.VISIBLE);
        }

    }

    //Game Reset After Draw Or Game Over

    public void resetGame (View v) {

        isGameOver = false;
        currentPlayer = Player.ONE;
        resetScoreBoard();
        btn2.setVisibility(View.INVISIBLE);
    }

    //Resetting Score Board

    private void resetScoreBoard () {

        for(int i=0;i<gridLayout.getChildCount();i++){
            ImageView imageView = (ImageView) gridLayout.getChildAt(i);
            imageView.setImageDrawable(null);
            imageView.setAlpha(0.2f);

        }


        for(int i=0;i<scoreBoard.length;i++){
            scoreBoard[i] = Player.NULL;
        }

    }

    //Checking Draw Function

    private boolean checkForDraw() {
        int i;
        for(i=0;i<scoreBoard.length;i++){
            if(scoreBoard[i] == Player.NULL) break;
        }
        if((i == scoreBoard.length) && !isGameOver) return true;
        else return false;
    }


}

package com.example.walter.connectthree;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.support.v7.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private final int YELLOW = 0;
    private final int RED = 1;
    private final int DRAW = 5;
    private final int BLANK = -1;

    private int activePlayer = YELLOW; // 0 for yellow, 1 for red

    private int[] gamePosition = {BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK, BLANK};

    private int result = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int checkWin() {
        if(gamePosition[0] == gamePosition[1] && gamePosition[1] == gamePosition[2] && gamePosition[0] != -1) {
            return gamePosition[0];
        } else if(gamePosition[3] == gamePosition[4] && gamePosition[4] == gamePosition[5] && gamePosition[3] != -1) {
            return gamePosition[3];
        } else if(gamePosition[6] == gamePosition[7] && gamePosition[7] == gamePosition[8] && gamePosition[6] != -1) {
            return gamePosition[6];
        } else if(gamePosition[0] == gamePosition[3] && gamePosition[3] == gamePosition[6] && gamePosition[0] != -1) {
            return gamePosition[0];
        } else if(gamePosition[1] == gamePosition[4] && gamePosition[4] == gamePosition[7] && gamePosition[1] != -1) {
            return gamePosition[1];
        } else if(gamePosition[2] == gamePosition[5] && gamePosition[5] == gamePosition[8] && gamePosition[2] != -1) {
            return gamePosition[2];
        } else if(gamePosition[0] == gamePosition[4] && gamePosition[4] == gamePosition[8] && gamePosition[0] != -1) {
            return gamePosition[0];
        } else if(gamePosition[2] == gamePosition[4] && gamePosition[4] == gamePosition[6] && gamePosition[2] != -1) {
            return gamePosition[2];
        } else {
            return -1;
        }
    }

    public boolean isDraw() {
        for(int i = 0;i < gamePosition.length;i++) {
            if(gamePosition[i] == BLANK) {
                return false;
            }
        }
        return true;
    }

    public void dropIn(View view) {
        if (result == -1) {
            ImageView counter = (ImageView) view;
            int tag = Integer.parseInt(counter.getTag().toString());
            TextView resultText = (TextView) findViewById(R.id.winnerTextView);
            Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
            if (activePlayer == YELLOW) {
                if (gamePosition[tag] == -1) {
                    counter.setTranslationY(-1500);
                    counter.setImageResource(R.drawable.yellow);
                    gamePosition[tag] = YELLOW;
                    counter.animate().translationY(0).rotation(3600).setDuration(500);
                    result = checkWin();
                    if (result == YELLOW) {
                        Toast.makeText(this, "Yellow wins!", Toast.LENGTH_SHORT).show();
                        resultText.setText(R.string.yellowWins);
                        resultText.setVisibility(View.VISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);
                    } else if(isDraw()) {
                        result = DRAW;
                        resultText.setText(R.string.drawText);
                        resultText.setVisibility(View.VISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);
                    }
                    activePlayer = RED;
                } else if (gamePosition[tag] == RED) {
                    counter.setImageResource(R.drawable.red);
                } else if (gamePosition[tag] == YELLOW) {
                    counter.setImageResource(R.drawable.yellow);
                }
            } else if (activePlayer == RED) {
                if (gamePosition[tag] == -1) {
                    counter.setTranslationY(-1500);
                    counter.setImageResource(R.drawable.red);
                    gamePosition[tag] = RED;
                    counter.animate().translationY(0).rotation(3600).setDuration(500);
                    result = checkWin();
                    if (result == RED) {
                        Toast.makeText(this, "Red wins!", Toast.LENGTH_SHORT).show();
                        resultText.setText(R.string.redWins);
                        resultText.setVisibility(View.VISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);
                    } else if(isDraw()) {
                        result = DRAW;
                        resultText.setText(R.string.drawText);
                        resultText.setVisibility(View.VISIBLE);
                        playAgainButton.setVisibility(View.VISIBLE);
                    }
                    activePlayer = YELLOW;
                } else if (gamePosition[tag] == RED) {
                    counter.setImageResource(R.drawable.red);
                } else if (gamePosition[tag] == YELLOW) {
                    counter.setImageResource(R.drawable.yellow);
                }
            }
        }
    }

    public void resetGame(View view) {
        TextView resultText = (TextView) findViewById(R.id.winnerTextView);
        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);
//        ImageView[] imageViews = {
//                (ImageView) findViewById(R.id.imageView1),
//                (ImageView) findViewById(R.id.imageView2),
//                (ImageView) findViewById(R.id.imageView3),
//                (ImageView) findViewById(R.id.imageView4),
//                (ImageView) findViewById(R.id.imageView5),
//                (ImageView) findViewById(R.id.imageView6),
//                (ImageView) findViewById(R.id.imageView7),
//                (ImageView) findViewById(R.id.imageView8),
//                (ImageView) findViewById(R.id.imageView9)
//        };
        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        activePlayer = YELLOW;
        result = -1;

        for(int i = 0;i < gamePosition.length;i++) {
            gamePosition[i] = BLANK;
        }

//        for(ImageView imageView: imageViews) {
//            imageView.setImageResource(android.R.color.transparent);
//        }

        for(int i = 0;i < gridLayout.getChildCount();i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
//            counter.setImageResource(android.R.color.transparent);
            counter.setImageDrawable(null);
        }

        resultText.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
    }
}

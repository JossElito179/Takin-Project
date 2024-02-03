package takin.rotate.models;

import javafx.scene.control.Button;

public class EventCTRL  {

    public void rotate90(Button[][]ListBtns,Button[][] ListBtns2){
        int num_columns = ListBtns.length;
        for (int i = 0; i < num_columns; i++) {
            int k = ListBtns[i].length-1;
            for (int j = 0; j < ListBtns[i].length; j++) {
                if (k > -1) {
                    ListBtns2[i][j].setText(ListBtns[k][i].getText());
                    System.out.println("ij="+i+" "+j+"et pour ki="+k+" "+i+" lettre ="+ListBtns[k][i].getText());
                    k--;
                }
            }
        }
    }

    public void rotateM90(Button[][]ListBtns,Button[][] ListBtns2){
        int num_columns = ListBtns.length;
        int k =ListBtns[0].length-1;
        for (int i = 0; i <num_columns ; i++) {
            for (int j = 0; j <ListBtns[i].length ; j++) {
                if (k >-1) {
                    ListBtns2[i][j].setText(ListBtns[j][k].getText());
                    System.out.println("ij="+i+" "+j+"et pour ki="+j+" "+k+" lettre ="+ListBtns[j][k].getText());
                }
            }
            k--;
        }
    }
}

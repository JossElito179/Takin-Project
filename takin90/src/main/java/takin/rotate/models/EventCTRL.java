package takin.rotate.models;

public class EventCTRL  {

    public void rotate90(ImageInfo[][]ListBtns,ImageInfo[][] imageInf){
        int num_columns = ListBtns.length;
        for (int i = 0; i < num_columns; i++) {
            int k = ListBtns[i].length-1;
            for (int j = 0; j < ListBtns[i].length; j++) {
                if (k > -1) {
                    imageInf[i][j].button.setGraphic(ListBtns[k][i].button.getGraphic());
                    imageInf[i][j].name=ListBtns[k][i].name;
                    k--;
                }
            }
        }
        for (int i = 0; i < num_columns; i++) {
            for (int j = 0; j < ListBtns[i].length; j++) {
                if (imageInf[i][j].button != null) {
                    ListBtns[i][j].button.setGraphic(imageInf[i][j].button.getGraphic());
                    ListBtns[i][j].name=imageInf[i][j].name;
                }
            }
        }
    }

    public void rotateM90(ImageInfo[][]ListBtns,ImageInfo[][] imageInf){
        int num_columns = ListBtns.length;
        int k =ListBtns[0].length-1;
        for (int i = 0; i <num_columns ; i++) {
            for (int j = 0; j <ListBtns[i].length ; j++) {
                if (k >-1) {
                    imageInf[i][j].button.setGraphic(ListBtns[j][k].button.getGraphic());
                    imageInf[i][j].name=ListBtns[j][k].name;
                }
            }
            k--;
        }
        for (int i = 0; i < num_columns; i++) {
            for (int j = 0; j < ListBtns[i].length; j++) {
                ListBtns[i][j].button.setGraphic(imageInf[i][j].button.getGraphic());
                ListBtns[i][j].name=imageInf[i][j].name;
            }
        }
    }

}


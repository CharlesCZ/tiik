package src;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {
    @FXML
    public TextField charQuantity;
    @FXML
    public TextField entropy;
    @FXML
    public MenuItem open;
    @FXML
    public TextArea fileContent;
    @FXML
    public TableView tableSigns;
    @FXML
    public TableView tableCodes;
    @FXML
    public TextArea preOrderPath;


    private static final SignService signService=new SignService();
    @FXML
    public TextArea bitsQuantity;
    @FXML
    public TextArea encoded;
    @FXML
    public TextArea decoded;
    @FXML
    public TextArea compression;

    private void readFile(File file) throws IOException{
        FileReader fr = new FileReader(file);
        int quantity= signService.countChars(fr);

        charQuantity.appendText(Integer.toString(quantity));
        //print file content
        fileContent.setText(signService.getContent());

//        System.out.println(signService.getCharsMap());

        signService.setCharStatistics(quantity);

        tableSigns.getItems().addAll(signService.getSigns());

        signService.setEntropy();
        entropy.setText(Double.toString(signService.getEntropy()));

        Huffman huffman=new Huffman();
        huffman.setHuffmanQueue(10);//10
        huffman.insertSignsToQueue(signService.getSigns());

        Node root=huffman.getHuffmanTree();
        //  huffman.traversePreOrder(root);
        huffman.preorder(root,"");


//        huffman.getCodes().forEach(System.out::println);
        tableCodes.getItems().addAll(huffman.getCodeList());
        String huffmanPreorderPath = huffman.preorderPath(root);
        String huffmanEncoded = huffman.encode(signService.getContent());

        preOrderPath.setText(huffmanPreorderPath);
        encoded.setText(  huffmanEncoded);
        bitsQuantity.setText( Integer.toString( signService.getBitsQuantity(huffmanPreorderPath,huffmanEncoded)) );
        String huffmanDecoded = huffman.decode(huffmanPreorderPath,huffmanEncoded);
        decoded.setText(huffmanDecoded);

        CompresionRate( huffmanDecoded, huffmanEncoded, signService.getBitsQuantity(huffmanPreorderPath,huffmanEncoded), quantity);
    }

    private void openFile(File file) {
        try {
           readFile(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    Main.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

    private void CompresionRate(String decoded, String encoded, int ibitsQuantity, int quantity){
//        final double dlenght =  decoded.length(); //its equal to file lenght
//        final double elenght = encoded.length();
        double charsQuantity= quantity*8;
        double bitsQuantity = (double)ibitsQuantity;
        //Wk=1-SK=(wielkośćDanychNieskompresowanych - WielkośćDanychSkompresowanych)/wielkośćDanychNieskompresowanych
        final double Wk = ((charsQuantity) - bitsQuantity)/(charsQuantity);
        //Sk=100%*wielkoścdanychSkompresowanych/wielkośćDanychnieskompresowanych
        final double Sk =  100*bitsQuantity/charsQuantity;
        final double Ss = 1 - (bitsQuantity/(charsQuantity));



        compression.appendText("compression rate Sk:\t "+ Sk + "\n");
        compression.appendText("compression ratio Wk:\t "+ Wk + "\n");
        compression.appendText("Wk = 1 - Sk:\t\t\t "+ Ss + "\n");



    }

    public Controller(){}

    @FXML
    public void handleOpenAction(final ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(open.getParentPopup().getScene().getWindow());
        if (file != null) {
            openFile(file);
        }
    };

    @FXML
    private void initialize()
    {

        TableColumn<String, Sign> colCharacter = new TableColumn<>("Character: ");
        colCharacter.setCellValueFactory(new PropertyValueFactory<>("character"));

        TableColumn<String, Sign> colProbability = new TableColumn<>("Probability: ");
        colProbability.setCellValueFactory(new PropertyValueFactory<>("probability"));

        TableColumn<String, Sign> colUitOfInformation = new TableColumn<>("UnitOfInformation: ");
        colUitOfInformation.setCellValueFactory(new PropertyValueFactory<>("unitOfInformation"));

        TableColumn<String, Sign> colOccurrences = new TableColumn<>("Occurrences: ");
        colOccurrences.setCellValueFactory(new PropertyValueFactory<>("occurences"));

        tableSigns.getColumns().add(colCharacter);
        tableSigns.getColumns().add(colProbability);
        tableSigns.getColumns().add(colUitOfInformation);
        tableSigns.getColumns().add(colOccurrences);

        TableColumn<String, Sign> colCharacter2 = new TableColumn<>("Character: ");
        colCharacter2.setCellValueFactory(new PropertyValueFactory<>("character"));

        TableColumn<String, Sign> colCode = new TableColumn<>("Code: ");
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));

        tableCodes.getColumns().add(colCharacter2);
        tableCodes.getColumns().add(colCode);


    }


}

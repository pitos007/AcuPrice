/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package footjoy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
        
/**
 *
 * @author UPatryk
 */
public class PriceListUpdater extends FJFileManager implements Printer, Writer {
    //private FJFileManager fileMgr = new FJFileManager();
    private ExtractorManager em = new ExtractorManager();
    private List<String> headers;
    private List<Map<String, List<String>>> priceFileList = new ArrayList<>();
    private Map<String, List<String>> priceList = new LinkedHashMap<>();
    private List<Map<String, List<String>>> priceChangesList = new ArrayList<>();
    private Printer pr;
    private String path = "C:\\Users\\upatryk\\Documents\\NetBeansProjects\\AcuPrice\\src\\FileContainer\\";
    
    public PriceListUpdater() throws DuplicateElementException{
        //this.headers = fileMgr.getFileNames();
        this.headers = super.getFileNames();
        //this.priceList = em.generatePriceMap(); // 12345 [10, 11, 12]
        this.priceList = em.generatePriceMap(); // 12345 [10, 11, 12]
    }
    
    
    public void readAndUpdatePriceFile() throws MissingFileException{
        System.out.println("Updating prices...");
        System.out.println("Creating priceChanges list...");
        System.out.println("available files:");
        for(String prFile : this.headers){
            File inFile = new File(path + prFile + ".csv");
            if (!inFile.exists()) {
                listMissingFiles();
                throw new MissingFileException("file does not exist");
            }else{
                System.out.println(inFile);
                Scanner fs = null;
                Map<String, List<String>> priceFileMap = new LinkedHashMap<>();
                try {
                    Scanner ls = null;
                    String currentLine;
                    fs = new Scanner(new BufferedReader(new FileReader(inFile)));
                    while(fs.hasNextLine()){
                        currentLine = fs.nextLine();
                        String strEmpty = currentLine.replaceAll(",,", ", ,");   //replace 12345,,54321 into 12345," ",54321
                        List<String> origList = new ArrayList<>();
                        ls = new Scanner(strEmpty);
                        ls.useDelimiter(",");
                        while(ls.hasNext()){
                            origList.add(ls.next()); //[TRADEUK1, 92250-32, PERF SHORTS BLACK, 8Q, 67, PR, 10112, 311217, 1, 25,
                        }
                        // uniqueCode must be unique in the Map
                        String uniqueCode = origList.get(1) + origList.get(8) + origList.get(6);  // Code1Qty12Date16, 92250-3212010116
                        String templCode = formatCode(origList.get(1)); // 92250
                        String origPrice = origList.get(9); // 25
                        String codeDesc = origList.get(2);
                        if (isInPriceList(templCode)) { // 92250 in 92250 [10, 11, 12]
                            List<String> updatedTmpList = updatePrice(origList, templCode, origPrice, uniqueCode, codeDesc);
                            //tmpList = updatedTmpList;
                            priceFileMap.put(uniqueCode, updatedTmpList);
                        }
                        else{
                            priceFileMap.put(uniqueCode, origList);
                        }
                    }
                }
                catch (Exception e) {
                    System.err.println("Probelm with reading " + prFile + " file" + e);
                }   PriceListUpdater.this.priceFileList.add(priceFileMap);
            }
        } //pr.printAllPriceFiles(priceFileList);
    }
    
    public void listMissingFiles(){
        List<String> fileNamesList = new ArrayList<>();
        headers.stream().forEach((fileName) -> {
            File inFile = new File(path + fileName + ".csv");
            if (!inFile.exists()) {
                fileNamesList.add(fileName);
            }
        });
        String missingFiles = "";
        missingFiles = fileNamesList.stream()
                .map((String missingFile) -> ", " + missingFile)
                .reduce(missingFiles, String::concat);
        System.out.println("missing files: " + missingFiles);
    }
    
    
    
    
    
    // converts 12345P, 12346S to 12345P, 12346
   public static String formatCode(String cd){
        String ncd = null;
        if (cd.length()>5) {
            ncd = cd.substring(0, 6);
        }
        else{
            ncd = cd;
        }
        Pattern p1 = Pattern.compile("(?i)[qadhrbpe]");
        Pattern p2 = Pattern.compile("(?i)(e-)");
        Matcher matcher = p1.matcher(ncd);
        if (!matcher.find()) {
            matcher.reset();
            matcher = p2.matcher(ncd);
            if (!(matcher.find())&&(ncd.length() > 4)) {
                ncd = ncd.substring(0,5);
            }
        }
        return ncd;
    }
    
    // checks whether template code 12345 exists in the price Map 12345 [10, 11, 12]
    public boolean isInPriceList(String code){
        return priceList.containsKey(code);
    }
    
    
    // the method changes the current price if the new price does not equal the current price
    // then it updates the priceChangesList
    public List<String> updatePrice(List<String> origList, String templCode, String origPrice, String uniqueCode, String codeDesc) throws MissingRoundingException {
        List<String> mfMapKeyList = getListFromMapKey(templCode); //[95890/1/2/3/4, Womens FJ Lambswool, 99.99, 44.2, 47.4, 43.0 ...]
        String priceListName = origList.get(0); //TRADEUK1
        int priceIndex = getIndexHeaderPrice(priceListName); //2
        String newPrice = mfMapKeyList.get(priceIndex - 1); // 99.99
        if (newPrice.length() > 6) {
            throw new MissingRoundingException("new price" + newPrice + " must be rounded up to 2 dec places");
        }
        if ((!newPrice.equals(origPrice))&&(!newPrice.equals("0"))) { //99.99 != 25.50
            //System.out.println("original list: " + origList);
            //System.out.println("search key: " + templCode);
            //System.out.println("found in: " + mfMapKeyList);
            //System.out.println("price file name: " + priceListName);
            //System.out.println("new price in " + priceListName + " is " + newPrice);
            List<String> updatedList = origList; //TRADEUK1,95890M,STA-COOLER,4R,40,EA,10100,311217,1,25.50 ...
            updatedList.set(9, newPrice); //TRADEUK1,95890M,STA-COOLER,4R,40,EA,10100,311217,1,99.99 ...
            //System.out.println("updated list: " + updatedList);
            //System.out.println("-------------------------");
            List<String> reportList = new ArrayList<>();
            reportList.add(origList.get(1));
            reportList.add(codeDesc);
            reportList.add(priceListName);
            reportList.add(origPrice);
            reportList.add(newPrice);
            Map<String, List<String>> priceChanges = new LinkedHashMap<>();
            priceChanges.put(uniqueCode, reportList); //95890M1010116 [95890M, STA-COOLER, TRADEUK, 25.50, 99.99]
            this.priceChangesList.add(priceChanges); //[95890M1010116 [95890M, STA-COOLER, TRADEUK, 25.50, 99.99]], [95890M1010116[95890M, STA-COOLER, TRADEEU, 28.50, 99.99]]
            return updatedList;
        }
        else{
            return origList;
        }
    }
    
    
    // gets a list of tokens from the current price List Map
    public List<String> getListFromMapKey(String key){
        List<String> listFromKey = priceList.get(key); //[95890/1/2/3/4, Womens FJ Lambswool, 34.50, 44.2, 47.4, 43.0 ...]
        //System.out.println(listFromKey);
        //System.out.println("list from map: " + listFromKey);
        return listFromKey;
    }
    
    public int getIndexHeaderPrice(String prc){
        int hindex = this.em.getHeaders().lastIndexOf(prc);
        //System.out.println(prc + " is at " + hindex);
        return hindex;
    }
    
    public List<Map<String, List<String>>> getPriceChangesList(){
        return this.priceChangesList;
    }
    
    public List<Map<String, List<String>>> getPriceFileList(){
        return this.priceFileList;
    }

    

    @Override
    public void printPriceListMap() {
        
    }

    @Override
    public void printAllPriceFiles(List<Map<String, List<String>>> priceFileList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPriceList(List<String> prListMap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    @Override
    public String createFileName(String fileName){
        //String outPath = fileMgr.getOutPathName();
        String outPath = super.getOutPathName();
        DateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
        Date dateTime = new Date();
        String dateTimeStr = dateFormat.format(dateTime);
        String outFileName = outPath + dateTimeStr + fileName + ".csv";
        System.out.println("creating output file: ");
        System.out.println(outFileName);
        return outFileName;
    }


    

    @Override
    public String createFileName() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}

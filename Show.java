package TestImage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author iadiv
 */
public class Show extends javax.swing.JFrame {

    /**
     * Creates new form Show
     */
    
    Dimension screenSize = null, size;
    int height, width;
    
    JLabel lblImageNo;
    JTextField txtImageNo;
    JLabel lblKNN;
    JTextField txtKNN;
    
    JButton button;
    
    ImageIcon testImage;
    JLabel lblTestImage;   //get the test image
    ImageIcon []trainImage;
    int trainImageLen = 0;
    JLabel []lblTrainImage;  //get the train images
    
    String imgfile_dir;
    String testImagePath, trainImagePath;
    int i = 0, row = 0, col = 0;
    ArrayList<String> arrTrainFile = new ArrayList<String>();  //get the path of train image value
    String line;    //for train images
    InputStream is; //for train image handling
    BufferedReader br;  //for train image handling
    
    public Show() {
        initComponents();
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //height = screenSize.height;
        //width = screenSize.width;
        this.setSize(screenSize);
        showWidgets();
    }
    
    public void showWidgets()
    {
        lblTestImage = new JLabel();//show test image on the panel
        lblTestImage.setBackground(new java.awt.Color(255,255,255));
        lblTestImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTestImage.setOpaque(true);
        size = lblTestImage.getPreferredSize();
        lblTestImage.setBounds(100, 150,40, 40);
        showPanel.add(lblTestImage);
        
        //add train images on panel
       /* lblTrainImage = new JLabel[10];
        row = 210;
        col = 100;
        for(i=0;i<10;i++)
        {
            lblTrainImage[i] = new JLabel();//show test image on the panel
            lblTrainImage[i].setBackground(new java.awt.Color(255,255,255));
            lblTrainImage[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            lblTrainImage[i].setOpaque(true);
            size = lblTrainImage[i].getPreferredSize();
            lblTrainImage[i].setBounds(col, row, 40, 40);
            showPanel.add(lblTrainImage[i]);
            col+=50;
        }*/
        
        lblImageNo = new JLabel();
        lblImageNo.setText("Test Image No.[0-9999]: ");
        lblImageNo.setBackground(new java.awt.Color(255,255,255));
        lblImageNo.setFont(new Font("Arial",Font.BOLD, 16));
        lblImageNo.setForeground(Color.blue);
        lblImageNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImageNo.setOpaque(true);
        lblImageNo.setBounds(10,  50,200, 20);            
        showPanel.add(lblImageNo);
        
        txtImageNo = new JTextField(5);
        txtImageNo.setBackground(Color.lightGray);
        txtImageNo.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtImageNo.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtImageNo.setForeground(new java.awt.Color(0, 51, 255));
        txtImageNo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtImageNo.setOpaque(true);
        txtImageNo.setBounds(220, 50, 100, 25);
        showPanel.add(txtImageNo);
        
        
        lblKNN = new JLabel();
        lblKNN.setText("KNN: ");
        lblKNN.setBackground(new java.awt.Color(255,255,255));
        lblKNN.setFont(new Font("Arial",Font.BOLD, 16));
        lblKNN.setForeground(Color.blue);
        lblKNN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblKNN.setOpaque(true);
        lblKNN.setBounds(320,  50,200, 20);            
        showPanel.add(lblKNN);
        
        txtKNN = new JTextField(5);
        txtKNN.setBackground(Color.lightGray);
        txtKNN.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtKNN.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtKNN.setForeground(new java.awt.Color(0, 51, 255));
        txtKNN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtKNN.setOpaque(true);
        txtKNN.setBounds(550, 50, 100, 25);
        showPanel.add(txtKNN);
        
        button = new JButton();
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBounds(700, 50, 100, 25);
        button.setText("Submit");
        button.addActionListener(btnListen);
        showPanel.add(button);
    }
    
    ImageIcon ReadFileFromResource(String Path) {
        ImageIcon ic = null;
        try {
            URL ur = getClass().getResource(Path);
            //System.out.println("UR: "+ur);
            ic = new ImageIcon(ur);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return ic;
    }
    public void text(int testImageNum){
        try{
            String testimagenum = testImageNum+".png";
          //  System.out.println("yaha bhi dekh le"+testimagenum);
            BufferedReader in = new BufferedReader(new FileReader("\\G:\\Project\\ImgProcessUsingKNN\\src\\TestImage\\mapper\\test_map.txt"));
            String str;
            str = in.readLine();
            while((str = in.readLine())!= null){
                //System.out.println(str);
                String[] arr = str.split(",");
                String imagenumber = arr[0];
                if(imagenumber.equals(testimagenum))
                {
                String classname = arr[1];
                System.out.println("testclassname:\t"+classname);
                }
                else {
                    //System.out.println("Wrong");
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    int airplane=0,trainclass=0,automobile =0,bird =0,cat=0,deer=0,dog=0,frog=0,horse=0,ship=0,truck=0;
    int[] arr1;
    int train =0;
    int sum =0;
    public void fest(String trainImageNum, int testImageNum,int knn){
        try{
            String classname1="";
            String testimagenum = testImageNum+".png";
          //  System.out.println("yaha bhi dekh le"+testimagenum);
            BufferedReader ls = new BufferedReader(new FileReader("\\G:\\Project\\ImgProcessUsingKNN\\src\\TestImage\\mapper\\test_map.txt"));
            String str1;
            str1 = ls.readLine();
            while((str1 = ls.readLine())!= null){
                //System.out.println(str);
                String[] arr = str1.split(",");
                String imagenumber = arr[0];
                if(imagenumber.equals(testimagenum))
                {
                classname1 = arr[1];
               // System.out.println("testclassname:\t"+classname1);
                }
                else {
                    //System.out.println("Wrong");
                }
            }
            
            
            String trainimagename = trainImageNum+".png";
            //System.out.println("yaha bhi dekh le"+trainimagename);
            BufferedReader in = new BufferedReader(new FileReader("\\G:\\Project\\ImgProcessUsingKNN\\src\\TestImage\\mapper\\train_map.txt"));
            String str;
            str = in.readLine();
            while((str = in.readLine())!= null){
                //System.out.println(str);
                String[] arr = str.split(",");
                String imagenumber = arr[0];
               // System.out.println("see this    "+imagenumber);
                if(imagenumber.equals(trainimagename))
                {
                System.out.println("image number\t"+imagenumber+"  trainimagenumber\t"+trainimagename);
                String classname = arr[1];
                if(classname1.equals(classname)){
                   train= ++trainclass;
                   ++sum;
                  // System.out.println("Idhar dekh chutiye   "+sum);
                }
                if(classname1 != classname && classname == "airplane")
                {
                   arr1[0]= ++airplane;
                   ++sum;
                   System.out.println("Idhar dekh chutiye   "+arr1[0]);
                }
                if(classname1 != classname && classname == "automobile")
                {
                    arr1[1]=++automobile;
                    ++sum;
                }
                if(classname1 != classname && classname == "bird")
                {
                    arr1[2]=++bird;
                   ++sum;
                }
                if(classname1 != classname && classname == "cat")
                {
                    arr1[3]=++cat;
                    ++sum;
                }
                if(classname1 != classname && classname == "deer")
                {
                    arr1[4]=++deer;
                    ++sum;
                }
                if(classname1 != classname && classname == "dog")
                {
                    arr1[5]=++dog;
                    ++sum;
                }
                if(classname1 != classname && classname == "frog")
                {
                    arr1[6]=++frog;
                    ++sum;
                }
                if(classname1 != classname && classname == "horse")
                {
                    arr1[7]=++horse;
                    ++sum;
                }
                if(classname1 != classname && classname == "ship")
                {
                    arr1[8]=++ship;
                    ++sum;
                }
                if(classname1 != classname && classname == "truck")
                {
                    arr1[9]=++truck;
                    ++sum;
                }
               // System.out.println("trainclassname:\t"+trainclass);
                }
                else {
                    //System.out.println("Wrong");
                }
            }//if((str = in.readLine())== null){
            if((sum+1)==knn)
            {
                    for(int i=0;i<knn;i++)
                    {
                        System.out.println(arr1[i]);
                    }
            }
                    // }
               
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void showTestImage(int testImageNum)
    {
        System.out.println("Test Image No. is: "+"testImages/"+testImageNum+".png");
        testImage = ReadFileFromResource("testImages/"+testImageNum+".png"); //image icon object
        //set image on label
        lblTestImage.setIcon(testImage);
        text(testImageNum);
    }
    
    public  void showTrainImages(int knn, int testImageNum)
    {
        if(testImageNum < 0 ||  knn ==0 || knn < 1||knn>10)
        {
            System.err.println("Error: Your knn value is more than 10 or knn value is < 1");
            JOptionPane.showMessageDialog(null,"Error! Either KNN value is more than 10 or equal to 0");
        }
        else
        {
            is = getClass().getResourceAsStream("txtFile/rgbdiff"+testImageNum+".txt");
            br = new BufferedReader(new InputStreamReader(is));
            String line="";
            try {
                while ((line = br.readLine()) != null) {
                    if(!line.equals(""))
                        arrTrainFile.add(line);
                }
            } catch (IOException ex) {
                Logger.getLogger(Show.class.getName()).log(Level.SEVERE, null, ex);
            }
            //get images from path as per knn size
            trainImage = new ImageIcon[knn];
            lblTrainImage = new JLabel[knn];
            row = 210;
            col = 100;
            for(i=0;i<knn;i++)
            {
                System.out.println("Index Value: "+i);
            //    System.out.println("File Name: "+arrTrainFile.get(i));
                lblTrainImage[i] = new JLabel();//show test image on the panel
                trainImage[i] = ReadFileFromResource("trainImages/"+arrTrainFile.get(i)+".png"); 
                lblTrainImage[i].setIcon(trainImage[i]);
                lblTrainImage[i].setBackground(new java.awt.Color(255,255,0));
                lblTrainImage[i].setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
                lblTrainImage[i].setOpaque(true);
                size = lblTrainImage[i].getPreferredSize();
                lblTrainImage[i].setBounds(col, row, 40, 40);
                showPanel.add(lblTrainImage[i]);
                col+=50;
                fest(arrTrainFile.get(i),testImageNum,knn);
            }
            super.repaint();
           // this.dispose();
        }//end of else condition for test image checker
    }
    
    ActionListener btnListen = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            //rgbdiff
            if(e.getActionCommand() == "Submit")
            {
                int knn = Integer.parseInt(txtKNN.getText());
                int test = Integer.parseInt(txtImageNo.getText());
                showTestImage(test);
                showTrainImages(knn, test);
            }
        }
    };

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        showPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Show Rendered Images");

        showPanel.setBackground(new java.awt.Color(255, 255, 255));
        showPanel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout showPanelLayout = new javax.swing.GroupLayout(showPanel);
        showPanel.setLayout(showPanelLayout);
        showPanelLayout.setHorizontalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 174, Short.MAX_VALUE)
        );
        showPanelLayout.setVerticalGroup(
            showPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 162, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(showPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Show.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Show.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Show.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Show.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Show().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel showPanel;
    // End of variables declaration//GEN-END:variables
}

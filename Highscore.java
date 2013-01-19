import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.security.*;

public class Highscore extends JPanel implements ActionListener {

    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    private int score = 0;
 
    public Highscore(int score) {
        super(new GridBagLayout());
 		this.score = score;
        textField = new JTextField(20);
        textField.addActionListener(this);
 
        textArea = new JTextArea(50, 50);
        textArea.setEditable(false);
        textArea.setText(getHighscores());
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
 
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }

    public String getHighscores()
    {
    	// Submit highscore
        try
        {
        	URL myURL = new URL("http://swingtest.sopamo.de/");
	    	URLConnection connection = myURL.openConnection();
	    	connection.setDoOutput(true);

	        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
	        out.write("addscore=Moritur&score=102");
	        out.close();

	        BufferedReader in = new BufferedReader(
	                                    new InputStreamReader(
	                                    connection.getInputStream()));
	        String decodedString;
	        String completeReturn = "";
	        while ((decodedString = in.readLine()) != null) {
	            completeReturn += decodedString + "\n";
	        }
	        in.close();
	        return completeReturn;
    	} 
		catch (MalformedURLException e) { 
		    System.out.println("fail");
		} 
		catch (IOException e) {   
		    System.out.println("fail");
		}
		return "";
    }
 
    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();


        // Submit highscore
        try
        {
        	URL myURL = new URL("http://swingtest.sopamo.de/?addscore="+text+"&score="+score+"&hash="+MD5(score+text+"asoU2ud827348Bbwqii"));
	    	URLConnection connection = myURL.openConnection();
	    	connection.setDoOutput(true);

	        OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
	        out.write("addscore=Moritur&score=102");
	        out.close();

	        BufferedReader in = new BufferedReader(
	                                    new InputStreamReader(
	                                    connection.getInputStream()));
	        
	        in.close();
	        textArea.setText(getHighscores());
	        remove(textField);
    	} 
		catch (MalformedURLException e) { 
		    System.out.println("fail");
		} 
		catch (IOException e) {   
		    System.out.println("fail");
		}
    }

    public String MD5(String md5) {
    try {
        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
        byte[] array = md.digest(md5.getBytes("UTF-8"));
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
       }
        return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    catch (java.io.UnsupportedEncodingException e) {

    }
    return null;
}
}
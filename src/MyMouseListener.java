
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class MyMouseListener extends MouseAdapter {

    private ColorGrid colorGrid;

    public MyMouseListener(ColorGrid colorGrid) {
        this.colorGrid = colorGrid;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            colorGrid.labelPressed((JLabel) e.getSource());
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            String inputDateString = JOptionPane.showInputDialog("Enter date (in dd/MM/yyyy format)");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date inputDate = sdf.parse(inputDateString);
                Calendar cal = Calendar.getInstance(), calOrig = Calendar.getInstance();
                cal.setTime(inputDate);
                cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
                while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                    cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - 1);
                }
                calOrig.setTime(cal.getTime());
                System.out.println(sdf.format(cal.getTime()));
                MyColor[][] myColors = colorGrid.getMyColors();
                PrintWriter writer = new PrintWriter("dates.txt", "UTF-8");
                for (int col = 0; col < 52; col++) {
                    for (int row = 0; row < 7; row++) {
                        if (myColors[row][col].getShortName() != 0) {
                            cal.add(Calendar.DAY_OF_MONTH, col*7 + row);
                            writer.println(sdf.format(cal.getTime()) + "/" + myColors[row][col].getShortName());
                            cal.setTime(calOrig.getTime());
                        }
                    }
                }
                writer.close();
            } catch (ParseException ex) {
                Logger.getLogger(MyMouseListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyMouseListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(MyMouseListener.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}

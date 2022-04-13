import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ErrorMessage extends JFrame{
    private JButton OKButton;
    private JLabel errorMessageField;
    private JPanel panelMain;

    public ErrorMessage(String warnings)
    {
        super("Error!");
        this.setContentPane(this.panelMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        errorMessageField.setText(warnings);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}

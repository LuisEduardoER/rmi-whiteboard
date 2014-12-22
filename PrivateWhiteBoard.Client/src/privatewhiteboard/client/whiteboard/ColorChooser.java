/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client.whiteboard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Bui Thi Mai
 */
public class ColorChooser extends JPanel implements ActionListener, ChangeListener{
   public JColorChooser tcc;
    private Color currentColor;

    public ColorChooser() {
        tcc = new JColorChooser();
        currentColor = Color.BLUE;

        tcc.getSelectionModel().addChangeListener(this);

        //Remove the preview panel
        tcc.setPreviewPanel(new JPanel());

        //Override the chooser panels with our own
        AbstractColorChooserPanel panels[] = {new CrayonPanel()};
        tcc.setChooserPanels(panels);
        tcc.setColor(currentColor);

        add(tcc, BorderLayout.PAGE_END);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Color newColor = tcc.getColor();
        currentColor = newColor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Color newColor = JColorChooser.showDialog(ColorChooser.this,
                "Choose Background Color", currentColor);
        if (newColor != null) {
            currentColor = newColor;
        }
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    private class CrayonPanel extends AbstractColorChooserPanel implements ActionListener {

        JToggleButton redCrayon;
        JToggleButton yellowCrayon;
        JToggleButton greenCrayon;
        JToggleButton blueCrayon;

        public CrayonPanel() {
        }

        @Override
        public void updateChooser() {
            Color color = getColorFromModel();
            if (Color.red.equals(color)) {
                redCrayon.setSelected(true);
            } else if (Color.yellow.equals(color)) {
                yellowCrayon.setSelected(true);
            } else if (Color.green.equals(color)) {
                greenCrayon.setSelected(true);
            } else if (Color.blue.equals(color)) {
                blueCrayon.setSelected(true);
            }
        }

        protected JToggleButton createCrayon(String name, Border normalBorder) {
            JToggleButton crayon = new JToggleButton();
            crayon.setActionCommand(name);
            crayon.addActionListener(this);

            //Set the image or, if that's invalid, equivalent text.
            ImageIcon icon = createImageIcon("F:\\PROJECTS\\WHITE BOARD\\PrivateWhiteBoard\\PrivateWhiteBoard\\src\\Icons\\" + name + ".png");
            if (icon != null) {
                crayon.setIcon(icon);
                crayon.setToolTipText("The " + name + " crayon");
                crayon.setBorder(normalBorder);
            } else {
                crayon.setText("Image not found. This is the " + name + " button.");
                crayon.setFont(crayon.getFont().deriveFont(Font.ITALIC));
                crayon.setHorizontalAlignment(JButton.HORIZONTAL);
                crayon.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            }
            return crayon;
        }

        protected ImageIcon createImageIcon(String path) {
            java.net.URL imgURL = CrayonPanel.class.getResource(path);
            ImageIcon icon = new ImageIcon(path);
            if (icon != null) {
                return new ImageIcon(path);//(imgURL);
            } else {
                System.err.println("Couldn't find file: " + path);
                return null;
            }
        }

        @Override
        protected void buildChooser() {
            setLayout(new GridLayout(1, 0));

            ButtonGroup boxOfCrayons = new ButtonGroup();
            Border border = BorderFactory.createEmptyBorder(4, 4, 4, 4);

            redCrayon = createCrayon("red", border);
            boxOfCrayons.add(redCrayon);
            add(redCrayon);

            yellowCrayon = createCrayon("yellow", border);
            boxOfCrayons.add(yellowCrayon);
            add(yellowCrayon);

            greenCrayon = createCrayon("green", border);
            boxOfCrayons.add(greenCrayon);
            add(greenCrayon);

            blueCrayon = createCrayon("blue", border);
            boxOfCrayons.add(blueCrayon);
            add(blueCrayon);
        }

        @Override
        public String getDisplayName() {
            return "Crayons";
        }

        @Override
        public Icon getSmallDisplayIcon() {
            return null;
        }

        @Override
        public Icon getLargeDisplayIcon() {
            return null;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Color newColor = null;
            String command = ((JToggleButton) e.getSource()).getActionCommand();
            if ("green".equals(command)) {
                newColor = Color.green;
            } else if ("red".equals(command)) {
                newColor = Color.red;
            } else if ("yellow".equals(command)) {
                newColor = Color.yellow;
            } else if ("blue".equals(command)) {
                newColor = Color.blue;
            }
            getColorSelectionModel().setSelectedColor(newColor);
        }
    }

}


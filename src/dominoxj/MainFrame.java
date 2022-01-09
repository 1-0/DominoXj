/*
 * The MIT License
 *
 * Copyright 2021 a10.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package dominoxj;

import javafx.scene.Group;

import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

/**
 * class for creaing main frame for <b>DominoXj</b> game
 *
 * @author a10
 */
public class MainFrame {

    public static final int MENU = 0;
    public static final int TABLE = 1;
    public static final int SETTINGS = 2;
    private final Group root;
    private Menu menu = null;
    private Table table = null;
    private Settings settings = null;
    private int state = MENU;
    private final Main outer;

    MainFrame(Group root, final Main outer) {
        this.outer = outer;
        this.root = root;
    }

    public void changeState(int newState) {
        state = newState;
        if (state == MainFrame.MENU) {
            menu = new Menu();
            if (table != null) {
                root.getChildren().remove(table);
                table = null;
            }
            if (settings != null) {
                root.getChildren().remove(settings);
                settings = null;
            }
            root.getChildren().add(menu);
            menu.start();
        }
        if (state == MainFrame.TABLE) {
            table = new Table();
            if (menu != null) {
                root.getChildren().remove(menu);
                menu = null;
            }
            root.getChildren().add(table);
            table.start();
        }
        if (state == MainFrame.SETTINGS) {
            settings = new Settings();
            if (menu != null) {
                root.getChildren().remove(menu);
                menu = null;
            }
            root.getChildren().add(settings);
            settings.start();
        }
    }

    public static class SwingDialog {
        JPanel panel = new JPanel();
        JLabel label = new JLabel();
        JLabel text = new JLabel();
        public static int TYPE_OK = 0;
        public static int TYPE_YES_NO = 1;

        SwingDialog() {
            panel.setSize(new Dimension(200, 100));
            panel.setLayout(null);
            label.setVerticalAlignment(SwingConstants.BOTTOM);
            label.setBounds(20, 20, 200, 60);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(label);
            UIManager.put("OptionPane.minimumSize", new Dimension(400, 200));
        }

        public int getResult(String title, String text, int dialog) {
            int res = 0;
            label.setText(text);
            if (dialog == TYPE_YES_NO) {
                res = JOptionPane.showConfirmDialog(null, panel, title,
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
            } else {
                res = JOptionPane.showConfirmDialog(null, panel, title,
                        JOptionPane.CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
            }
            return (res);
        }
    ;
}
}

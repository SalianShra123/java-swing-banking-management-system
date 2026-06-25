package gui;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Insets;

final class GuiHelper {
    static final Color NAVY = new Color(0, 18, 46);
    static final Color BLUE = new Color(0, 76, 255);
    static final Color GREEN = new Color(0, 145, 72);
    static final Color BACKGROUND = new Color(255, 255, 255);
    static final Color TEXT = new Color(0, 0, 0);
    private static final Color BUTTON_TEXT = Color.WHITE;

    private GuiHelper() {
    }

    static void setupFrame(JFrame frame, String title) {
        frame.setTitle(title);
        frame.setSize(760, 560);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(BACKGROUND);
    }

    static JPanel page(String heading, String subheading) {
        JPanel root = new JPanel(new BorderLayout(0, 18));
        root.setBackground(BACKGROUND);
        root.setBorder(BorderFactory.createEmptyBorder(24, 34, 28, 34));

        JPanel header = new JPanel(new BorderLayout(0, 6));
        header.setOpaque(false);
        JLabel title = new JLabel(heading, SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(NAVY);
        JLabel subtitle = new JLabel(subheading, SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitle.setForeground(new Color(25, 25, 25));
        header.add(title, BorderLayout.NORTH);
        header.add(subtitle, BorderLayout.CENTER);

        root.add(header, BorderLayout.NORTH);
        return root;
    }

    static JPanel formPanel() {
        JPanel panel = new JPanel(new java.awt.GridBagLayout());
        panel.setOpaque(false);
        return panel;
    }

    static GridBagConstraints gbc(int x, int y) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.weightx = x == 1 ? 1 : 0;
        return gbc;
    }

    static JLabel label(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        label.setForeground(TEXT);
        return label;
    }

    static JButton button(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(BUTTON_TEXT);
        button.setBackground(BLUE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(true);
        button.setOpaque(true);
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return button;
    }

    static JButton secondaryButton(String text) {
        JButton button = button(text);
        button.setBackground(new Color(40, 40, 40));
        button.setForeground(BUTTON_TEXT);
        return button;
    }

    static JButton successButton(String text) {
        JButton button = button(text);
        button.setBackground(GREEN);
        button.setForeground(BUTTON_TEXT);
        return button;
    }

    static void refresh(JFrame oldFrame, JFrame newFrame) {
        oldFrame.dispose();
        newFrame.setVisible(true);
    }

    static void addCentered(JPanel panel, Component component) {
        panel.add(component, BorderLayout.CENTER);
    }
}

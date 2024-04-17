package com.mycompany.metatraderbrokeradmin;
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.tree.*;
import javax.swing.table.*;

public class JTreeTableExample {

    public static void main(String[] args) {
        JFrame frame = new JFrame("JTreeTableExample");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JTree model
        DefaultTreeModel treeModel = new DefaultTreeModel(new DefaultMutableTreeNode("Root"));
        DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode("Level 1");
        treeModel.insertNodeInto(parentNode, new DefaultMutableTreeNode("Item 1"), 0);
        treeModel.insertNodeInto(parentNode, new DefaultMutableTreeNode("Item 2"), 1);
//        treeModel.insertNodeInto(treeModel.mu, parentNode, 0);

        // Create a JTree
        JTree tree = new JTree(treeModel);

        // Create a JTable model
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Column 1");
        tableModel.addColumn("Column 2");
        tableModel.addRow(new Object[]{"Row 1", "Value 1"});
        tableModel.addRow(new Object[]{"Row 2", "Value 2"});

        // Create a JTable
        JTable table = new JTable(tableModel);

        // Create a JPanel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(tree, BorderLayout.WEST);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        // Add the JPanel to the frame
        frame.getContentPane().add(panel);

        // Pack the frame
        frame.pack();

        // Show the frame
        frame.setVisible(true);
    }
}

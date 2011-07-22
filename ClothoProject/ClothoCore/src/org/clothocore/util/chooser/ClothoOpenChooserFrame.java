/* This is the connector for the FeaturesLibrary function.
 * 
 Copyright (c) 2008 The Regents of the University of California.
 All rights reserved.
 Permission is hereby granted, without written agreement and without
 license or royalty fees, to use, copy, modify, and distribute this
 software and its documentation for any purpose, provided that the above
 copyright notice and the following two paragraphs appear in all copies
 of this software.

 IN NO EVENT SHALL THE UNIVERSITY OF CALIFORNIA BE LIABLE TO ANY PARTY
 FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES
 ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF
 THE UNIVERSITY OF CALIFORNIA HAS BEEN ADVISED OF THE POSSIBILITY OF
 SUCH DAMAGE.

 THE UNIVERSITY OF CALIFORNIA SPECIFICALLY DISCLAIMS ANY WARRANTIES,
 INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE
 PROVIDED HEREUNDER IS ON AN "AS IS" BASIS, AND THE UNIVERSITY OF
 CALIFORNIA HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES,
 ENHANCEMENTS, OR MODIFICATIONS..
 */


package org.clothocore.util.chooser;

import javax.swing.ImageIcon;

/**
 *
 * @author  Nade
 */
public class ClothoOpenChooserFrame extends javax.swing.JFrame {

    /** Creates new form ClothoOpenChooserFrame */
    public ClothoOpenChooserFrame() {
        initComponents();
    }
    
    public ClothoOpenChooserFrame(String n, ClothoOpenChooser c) {
        _name = n;
        
        _clotho_connection = c;
        initComponents();
    }

    /**
     * Returns the FileChooser from ClothoOpenChooserFrame
     * @return javax.swing.JFileChooser: openFileChooser
     */
    public javax.swing.JFileChooser getChooser() {
        return openFileChooser;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        openFileChooser = new javax.swing.JFileChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Clotho: Browse...");
        setIconImage(new ImageIcon("data/icons/ClothoIcon.png").getImage());

        openFileChooser.setCurrentDirectory(null);
        openFileChooser.setDialogTitle("Browse...");
        openFileChooser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openFileChooserActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(openFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(openFileChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void openFileChooserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openFileChooserActionPerformed
    if (evt.getActionCommand().equalsIgnoreCase(javax.swing.JFileChooser.APPROVE_SELECTION))
        _clotho_connection.openButtonSelected(openFileChooser);
    
    if (evt.getActionCommand().equalsIgnoreCase(javax.swing.JFileChooser.CANCEL_SELECTION))
        _clotho_connection.cancelButtonSelected(openFileChooser);
}//GEN-LAST:event_openFileChooserActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClothoOpenChooserFrame().setVisible(true);
            }
        });
    }

    private String _name;
    private ClothoOpenChooser _clotho_connection;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFileChooser openFileChooser;
    // End of variables declaration//GEN-END:variables

}

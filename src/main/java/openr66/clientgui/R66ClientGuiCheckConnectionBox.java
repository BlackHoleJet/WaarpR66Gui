/*
 * R66ClientGuiAboutBox.java
 */

package openr66.clientgui;

import org.jdesktop.application.Action;

/**
 * CheckConnection or Transfer Result Box class
 * @author Frederic Bregier
 *
 */
public class R66ClientGuiCheckConnectionBox extends javax.swing.JDialog {

    /**
     *
     */
    private static final long serialVersionUID = 7826586466146373102L;

    public R66ClientGuiCheckConnectionBox(java.awt.Frame parent) {
        super(parent);
        initComponents();
        getRootPane().setDefaultButton(closeButton);
    }

    @Action public void closeCheckConnectionBox() {
        dispose();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();
        javax.swing.JLabel appTitleConnLabel = new javax.swing.JLabel();
        javax.swing.JLabel hostLabel = new javax.swing.JLabel();
        javax.swing.JLabel appHostIDLabel = new javax.swing.JLabel();
        javax.swing.JLabel resultLabel = new javax.swing.JLabel();
        javax.swing.JLabel appResultLabel = new javax.swing.JLabel();
        javax.swing.JLabel imageLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(openr66.clientgui.R66ClientGuiApp.class).getContext().getResourceMap(R66ClientGuiCheckConnectionBox.class);
        setTitle(resourceMap.getString("title")+" "+Version.ID); // NOI18N
        setModal(true);
        setName("checkConnectionBox"); // NOI18N
        setResizable(false);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(openr66.clientgui.R66ClientGuiApp.class).getContext().getActionMap(R66ClientGuiCheckConnectionBox.class, this);
        closeButton.setAction(actionMap.get("closeCheckConnectionBox")); // NOI18N
        closeButton.setName("closeButton"); // NOI18N

        appTitleConnLabel.setFont(appTitleConnLabel.getFont().deriveFont(appTitleConnLabel.getFont().getStyle() | java.awt.Font.BOLD, appTitleConnLabel.getFont().getSize()+4));
        appTitleConnLabel.setText(resourceMap.getString("title.text")); // NOI18N
        appTitleConnLabel.setName("appTitleConnLabel"); // NOI18N

        hostLabel.setFont(hostLabel.getFont().deriveFont(hostLabel.getFont().getStyle() | java.awt.Font.BOLD));
        hostLabel.setText(resourceMap.getString("hostLabel.text")); // NOI18N
        hostLabel.setName("hostLabel"); // NOI18N

        appHostIDLabel.setText(R66ClientGui.GuiHostId); // NOI18N
        appHostIDLabel.setName("appHostIDLabel"); // NOI18N

        resultLabel.setFont(resultLabel.getFont().deriveFont(resultLabel.getFont().getStyle() | java.awt.Font.BOLD));
        resultLabel.setText(resourceMap.getString("resultLabel.text")); // NOI18N
        resultLabel.setName("resultLabel"); // NOI18N

        appResultLabel.setText(R66ClientGui.GuiResultat); // NOI18N
        appResultLabel.setName("appResultLabel"); // NOI18N

        imageLabel.setIcon(resourceMap.getIcon("imageLabel.icon")); // NOI18N
        imageLabel.setName("imageLabel"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageLabel)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hostLabel)
                            .addComponent(resultLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(appHostIDLabel)
                            .addComponent(appResultLabel))
                        .addGap(32, 32, 32))
                    .addComponent(appTitleConnLabel, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(appTitleConnLabel)
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(hostLabel)
                    .addComponent(appHostIDLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resultLabel)
                    .addComponent(appResultLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
            .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 177, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    // End of variables declaration//GEN-END:variables

}

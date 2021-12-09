import lombok.var;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application extends JFrame {
    private static JFrame frame;

    private static SectionDTO currentProduct = null;
    private static ProductDTO currentProduct = null;

    private static boolean editMode = false;
    private static boolean sectionMode = true;

    private static JButton btnAddSection = new JButton("Add Section");
    private static JButton btnAddProduct = new JButton("Add Product");
    private static JButton btnEdit = new JButton("Edit Data");
    private static JButton btnBack = new JButton("Back");
    private static JButton btnSave = new JButton("Save");
    private static JButton btnDelete = new JButton("Delete");

    private static Box menuPanel = Box.createVerticalBox();
    private static Box actionPanel = Box.createVerticalBox();
    private static Box comboPanel = Box.createVerticalBox();
    private static Box productPanel = Box.createVerticalBox();
    private static Box sectionPanel = Box.createVerticalBox();

    private static JComboBox comboSection = new JComboBox();
    private static JComboBox comboProduct = new JComboBox();

    private static JTextField textSectionName = new JTextField(30);
    private static JTextField textProductName = new JTextField(30);
    private static JTextField textProductSectionName = new JTextField(30);
    private static JTextField textProductPrice = new JTextField(30);

    private Application() {
        super("World Map");
        frame = this;
        frame.setPreferredSize(new Dimension(400, 500));
        frame.setMaximumSize(new Dimension(400, 500));
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                frame.dispose();
                DBConnection.closeConnection();
                System.exit(0);
            }
        });
        Box actionPanel = Box.createVerticalBox();
        sizeAllElements();
        frame.setLayout(new FlowLayout());

        // Menu
        menuPanel.add(btnAddSection);
        menuPanel.add(Box.createVerticalStrut(20));
        btnAddSection.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                editMode = false;
                sectionMode = true;
                menuPanel.setVisible(false);
                comboPanel.setVisible(false);
                sectionPanel.setVisible(true);
                productPanel.setVisible(false);
                Application.actionPanel.setVisible(true);
                pack();
            }
        });
        menuPanel.add(btnAddProduct);
        menuPanel.add(Box.createVerticalStrut(20));
        btnAddProduct.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                editMode = false;
                sectionMode = false;
                menuPanel.setVisible(false);
                comboPanel.setVisible(false);
                sectionPanel.setVisible(false);
                productPanel.setVisible(true);
                Application.actionPanel.setVisible(true);
                pack();
            }
        });
        menuPanel.add(btnEdit);
        menuPanel.add(Box.createVerticalStrut(20));
        btnEdit.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                editMode = true;
                menuPanel.setVisible(false);
                comboPanel.setVisible(true);
                sectionPanel.setVisible(false);
                productPanel.setVisible(false);
                Application.actionPanel.setVisible(true);
                pack();
            }
        });

        // ComboBoxes
        comboPanel.add(new JLabel("Section:"));
        comboPanel.add(comboSection);
        comboPanel.add(Box.createVerticalStrut(20));
        comboSection.addActionListener(e -> {
            String name = (String) comboSection.getSelectedItem();
            currentSection = SectionDAO.findByName((String) comboSection.getSelectedItem());
            sectionMode = true;
            sectionPanel.setVisible(true);
            productPanel.setVisible(false);
            fillSectionFields();
            pack();
        });
        comboPanel.add(new JLabel("Product:"));
        comboPanel.add(comboProduct);
        comboPanel.add(Box.createVerticalStrut(20));
        comboProduct.addActionListener(e -> {
            String name = (String) comboProduct.getSelectedItem();
            currentProduct = ProductDAO.findByName((String) comboProduct.getSelectedItem());
            sectionMode = false;
            sectionPanel.setVisible(false);
            productPanel.setVisible(true);
            fillProductFields();
            pack();
        });
        fillComboBoxes();
        comboPanel.setVisible(false);

        // Product Fields
        productPanel.add(new JLabel("Name:"));
        productPanel.add(textProductName);
        productPanel.add(Box.createVerticalStrut(20));
        productPanel.add(new JLabel("Section Name:"));
        productPanel.add(textProductSectionName);
        productPanel.add(Box.createVerticalStrut(20));
        productPanel.add(new JLabel("Price:"));
        productPanel.add(textProductPrice);
        productPanel.add(Box.createVerticalStrut(20));
        productPanel.setVisible(false);

        // Section Fields
        sectionPanel.add(new JLabel("Name:"));
        sectionPanel.add(textSectionName);
        sectionPanel.add(Box.createVerticalStrut(20));
        sectionPanel.setVisible(false);

        // Action Bar		
        Application.actionPanel.add(btnSave);
        Application.actionPanel.add(Box.createVerticalStrut(20));
        btnSave.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                save();
            }
        });
        Application.actionPanel.add(btnDelete);
        Application.actionPanel.add(Box.createVerticalStrut(20));
        btnDelete.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                delete();
            }
        });
        Application.actionPanel.add(btnBack);
        Application.actionPanel.add(Box.createVerticalStrut(20));
        btnBack.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                clearFields();
                menuPanel.setVisible(true);
                comboPanel.setVisible(false);
                sectionPanel.setVisible(false);
                productPanel.setVisible(false);
                Application.actionPanel.setVisible(false);
                pack();
            }
        });
        Application.actionPanel.setVisible(false);

        clearFields();
        actionPanel.setPreferredSize(new Dimension(300, 500));
        actionPanel.add(menuPanel);
        actionPanel.add(comboPanel);
        actionPanel.add(sectionPanel);
        actionPanel.add(productPanel);
        actionPanel.add(Application.actionPanel);
        setContentPane(actionPanel);
        //pack();
    }

    private static void sizeAllElements() {
        Dimension dimension = new Dimension(300, 50);
        btnAddSection.setMaximumSize(dimension);
        btnAddProduct.setMaximumSize(dimension);
        btnEdit.setMaximumSize(dimension);
        btnBack.setMaximumSize(dimension);
        btnSave.setMaximumSize(dimension);
        btnDelete.setMaximumSize(dimension);

        btnAddSection.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAddProduct.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnEdit.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDelete.setAlignmentX(Component.CENTER_ALIGNMENT);

        Dimension panelDimension = new Dimension(300, 300);
        menuPanel.setMaximumSize(panelDimension);
        comboPanel.setPreferredSize(panelDimension);
        actionPanel.setPreferredSize(panelDimension);
        productPanel.setPreferredSize(panelDimension);
        sectionPanel.setPreferredSize(panelDimension);

        comboSection.setPreferredSize(dimension);
        comboProduct.setPreferredSize(dimension);

        textProductSectionName.setPreferredSize(dimension);
        textProductName.setPreferredSize(dimension);
        textProductPrice.setPreferredSize(dimension);
        textSectionName.setPreferredSize(dimension);
    }

    private static void save() {
        if (editMode) {
            if (sectionMode) {
                currentSection.setName(textSectionName.getText());
                if (!SectionDAO.update(currentSection)) {
                    JOptionPane.showMessageDialog(null, "Error: update failed!");
                }
            } else {
                currentProduct.setName(textProductName.getText());
                currentProduct.setPrice(Long.parseLong(textProductPrice.getText()));

                SectionDTO section = SectionDAO.findByName(textProductSectionName.getText());
                if (section == null) {
                    JOptionPane.showMessageDialog(null, "Error: no such section!");
                    return;
                }
                currentProduct.setSectionId(section.getId());

                if (!ProductDAO.update(currentProduct)) {
                    JOptionPane.showMessageDialog(null, "Error: update failed!");
                }
            }
        } else {
            if (sectionMode) {
                SectionDTO section = new SectionDTO();
                section.setName(textSectionName.getText());

                if (!SectionDAO.insert(section)) {
                    JOptionPane.showMessageDialog(null, "Error: insertion failed!");
                    return;
                }

                comboSection.addItem(section.getName());
            } else {
                ProductDTO product = new ProductDTO();
                product.setName(textProductName.getText());
                product.setPrice(Long.parseLong(textProductPrice.getText()));

                SectionDTO section = SectionDAO.findByName(textProductSectionName.getText());
                if (section == null) {
                    JOptionPane.showMessageDialog(null, "Error: no such section!");
                    return;
                }
                product.setCountryId(section.getId());

                if (!ProductDAO.insert(product)) {
                    JOptionPane.showMessageDialog(null, "Error: insertion failed!");
                    return;
                }

                comboProduct.addItem(product.getName());
            }
        }
    }

    private static void delete() {
        if (editMode) {
            if (sectionMode) {
                var list = ProductDAO.findBySectionId(currentSection.getId());
                assert list != null;
                for (ProductDTO product : list) {
                    comboProduct.removeItem(product.getName());
                    ProductDAO.delete(product);
                }
                SectionDAO.delete(currentSection);
                comboSection.removeItem(currentSection.getName());

            } else {
                ProductDAO.delete(currentProduct);
                comboProduct.removeItem(currentProduct.getName());
            }
        }
    }

    private void fillComboBoxes() {
        comboSection.removeAllItems();
        comboProduct.removeAllItems();
        var sections = SectionDAO.findAll();
        var products = ProductDAO.findAll();
        for (SectionDTO section : sections) {
            comboSection.addItem(section.getName());
        }
        for (ProductDTO prosuct : products) {
            comboProduct.addItem(product.getName());
        }
    }

    private static void clearFields() {
        textSectionName.setText("");
        textProductName.setText("");
        textProductSectionName.setText("");
        textProductPrice.setText("");
        currentSection = null;
        currentProduct = null;
    }

    private static void fillSectionFields() {
        if (currentSection == null) {
            return;
        }
        textSectionName.setText(currentSection.getName());
    }

    private static void fillProductFields() {
        if (currentProduct == null) {
            return;
        }
        SectionDTO section = SectionDAO.findById(currentProduct.getSectionId());
        assert section != null;
        textProductSectionName.setText(section.getName());
        textProductName.setText(currentProduct.getName());
        textProductPrice.setText(String.valueOf(currentProduct.getPrice()));
    }

    public static void main(String[] args) {
        JFrame myWindow = new Application();
        myWindow.setVisible(true);
    }
}

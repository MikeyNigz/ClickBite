import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

public class ClickBiteGUI extends JFrame {
    // Store user data
    private Map<String, Map<String, String>> usersDatabase = new HashMap<>();
    private Map<String, String> currentUser = null;
    private JComboBox<String> accountDropdown; // Declare at class level

    public ClickBiteGUI() {
        // Set up JFrame
        setTitle("ClickBite");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // -------------------- Header Panel ------------------------
        JPanel headerPanel = new JPanel(new BorderLayout());
        Color headerColor = new Color(250, 212, 212);
        headerPanel.setBackground(headerColor);
        headerPanel.setPreferredSize(new Dimension(getWidth(), 100));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        // -------------------- Logo and Brand Name ------------------------
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        logoPanel.setOpaque(false);
        ImageIcon logoIcon = new ImageIcon("ClickBite_Images/ClickBite_logo.png");
        JLabel logoLabel = (logoIcon.getIconWidth() > 0)
                ? new JLabel(new ImageIcon(logoIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH)))
                : new JLabel("🍔", JLabel.LEFT);
        JLabel brandNameLabel = new JLabel("ClickBite");
        brandNameLabel.setFont(new Font("Arial", Font.BOLD, 28));
        brandNameLabel.setForeground(new Color(255, 56, 56));
        logoPanel.add(logoLabel);
        logoPanel.add(brandNameLabel);
        headerPanel.add(logoPanel, BorderLayout.WEST);

        // -------------------- Navigation ------------------------
        JPanel navPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 35));
        navPanel.setOpaque(false);
        String[] navItems = { "Menu", "Order Now", "Contact Us", "About Us" };
        for (String item : navItems) {
            JLabel navLabel = new JLabel(item);
            navLabel.setFont(new Font("Arial", Font.PLAIN, 18));
            navLabel.setForeground(new Color(255, 56, 56));
            navPanel.add(navLabel);
        }
        headerPanel.add(navPanel, BorderLayout.CENTER);

        // -------------------- Account Dropdown ------------------------
        JPanel accountPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 30));
        accountPanel.setOpaque(false);
        JLabel accountLabel = new JLabel("Account: ");
        accountLabel.setFont(new Font("Arial", Font.BOLD, 18));
        accountLabel.setForeground(new Color(255, 56, 56));
        String[] accountOptions = { "Profile", "My Orders", "My Cart", "Sign Up / Sign In", "Log Out" };
        accountDropdown = new JComboBox<>(accountOptions); // Initialize the class-level variable
        accountDropdown.setPreferredSize(new Dimension(180, 30));
        accountPanel.add(accountLabel);
        accountPanel.add(accountDropdown);
        headerPanel.add(accountPanel, BorderLayout.EAST);

        // ------------------------ Banner Panel with Button ------------------------
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setPreferredSize(new Dimension(getWidth(), 430));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));

        ImageIcon bannerIcon = new ImageIcon("ClickBite_Images/ClickBite_Banner.png");
        JLabel bannerLabel = (bannerIcon.getIconWidth() > 0)
                ? new JLabel(new ImageIcon(bannerIcon.getImage().getScaledInstance(1400, 430, Image.SCALE_SMOOTH)))
                : new JLabel("Welcome to ClickBite!", JLabel.CENTER);
        bannerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton orderNowButton = new JButton("Order Now");
        orderNowButton.setFont(new Font("Arial", Font.BOLD, 18));
        orderNowButton.setBackground(new Color(255, 56, 56));
        orderNowButton.setForeground(Color.WHITE);
        orderNowButton.setFocusPainted(false);
        orderNowButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderNowButton.setMaximumSize(new Dimension(200, 40));

        centerPanel.add(bannerLabel);
        centerPanel.add(Box.createVerticalStrut(20));
        centerPanel.add(orderNowButton);
        centerPanel.add(Box.createVerticalStrut(20));

        // ------------------------ Footer Panel ------------------------
        JPanel footerPanel = new JPanel(new GridLayout(1, 4));
        footerPanel.setBackground(new Color(102, 102, 102));
        String[] footerItems = { "Privacy Policy", "Terms & Conditions", "Menu", "About Us" };
        for (String item : footerItems) {
            JLabel label = new JLabel(item, JLabel.CENTER);
            label.setForeground(Color.WHITE);
            footerPanel.add(label);
        }

        // ------------------------ Add Panels to Frame ------------------------
        add(headerPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);

        // ---------------- Dropdown Action -------------------
        accountDropdown.addActionListener(e -> {
            String selected = (String) accountDropdown.getSelectedItem();
            if (selected.equals("Sign Up / Sign In")) {
                showSignUpSignInOptions();
            } else if (selected.equals("Profile")) {
                if (currentUser != null) {
                    showProfilePopup();
                } else {
                    JOptionPane.showMessageDialog(this, "Please sign in first", "Profile", JOptionPane.INFORMATION_MESSAGE);
                }
            } else if (selected.equals("Log Out")) {
                currentUser = null;
                JOptionPane.showMessageDialog(this, "Logged out successfully", "Log Out", JOptionPane.INFORMATION_MESSAGE);
            }
            // Reset the dropdown to avoid staying on the selected option
            accountDropdown.setSelectedIndex(0);
        });

        setVisible(true);
    }

    private void showSignUpSignInOptions() {
        String[] options = { "Customer Sign Up", "Customer Sign In", "Admin Sign Up", "Admin Sign In" };
        int choice = JOptionPane.showOptionDialog(this, "Choose an option:", "Sign Up / Sign In",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> showCustomerSignUpForm();
            case 1 -> showCustomerSignInForm();
            case 2 -> showAdminSignUpForm();
            case 3 -> showAdminSignInForm();
        }
    }

    private void showCustomerSignUpForm() {
        JPanel panel = new JPanel(new GridLayout(10, 2));
        JTextField fullNameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField usernameField = new JTextField();
        JTextField streetField = new JTextField();
        JTextField cityField = new JTextField();
        JTextField postalField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JPasswordField confirmPasswordField = new JPasswordField();
        JCheckBox termsCheck = new JCheckBox("I agree to terms & Privacy Policy");

        panel.add(new JLabel("Full name:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Email Address:"));
        panel.add(emailField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Street Address:"));
        panel.add(streetField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);
        panel.add(new JLabel("Postal/ZIP Code:"));
        panel.add(postalField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordField);
        panel.add(termsCheck);
        panel.add(new JLabel());

        int result = JOptionPane.showConfirmDialog(this, panel, "Customer Sign Up", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            if (!new String(passwordField.getPassword()).equals(new String(confirmPasswordField.getPassword()))) {
                JOptionPane.showMessageDialog(this, "Passwords don't match", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (!termsCheck.isSelected()) {
                JOptionPane.showMessageDialog(this, "You must agree to the terms", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            Map<String, String> userData = new HashMap<>();
            userData.put("fullName", fullNameField.getText());
            userData.put("email", emailField.getText());
            userData.put("username", usernameField.getText());
            userData.put("street", streetField.getText());
            userData.put("city", cityField.getText());
            userData.put("postal", postalField.getText());
            userData.put("password", new String(passwordField.getPassword()));
            
            usersDatabase.put(emailField.getText(), userData);
            currentUser = userData;
            
            JOptionPane.showMessageDialog(this, "Sign up successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void showCustomerSignInForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField emailField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JCheckBox rememberMe = new JCheckBox("Remember Me");
        
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(rememberMe);
        panel.add(new JLabel());
        panel.add(new JLabel("Forgot Password? Click here to reset"));
        panel.add(new JLabel());

        int result = JOptionPane.showConfirmDialog(this, panel, "Customer Sign In", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            
            if (usersDatabase.containsKey(email)) {
                Map<String, String> user = usersDatabase.get(email);
                if (user.get("password").equals(password)) {
                    currentUser = user;
                    JOptionPane.showMessageDialog(this, "Sign in successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect password", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showAdminSignUpForm() {
        JPanel panel = new JPanel(new GridLayout(8, 2));
        JTextField[] fields = createFields(panel, "Full name", "Email Address", "Username");
        JPasswordField password = new JPasswordField(), confirmPassword = new JPasswordField();
        panel.add(new JLabel("Password:"));
        panel.add(password);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPassword);
        panel.add(new JLabel("Role:"));
        panel.add(new JLabel("Order Manager"));
        JOptionPane.showConfirmDialog(this, panel, "Admin Sign Up", JOptionPane.OK_CANCEL_OPTION);
    }

    private void showAdminSignInForm() {
        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField userOrEmail = new JTextField();
        JPasswordField password = new JPasswordField();
        JCheckBox rememberMe = new JCheckBox("Remember Me");
        panel.add(new JLabel("Username or Email Address:"));
        panel.add(userOrEmail);
        panel.add(new JLabel("Password:"));
        panel.add(password);
        panel.add(rememberMe);
        panel.add(new JLabel());
        panel.add(new JLabel("Forgot Password? Click here to reset"));
        panel.add(new JLabel());
        JOptionPane.showConfirmDialog(this, panel, "Admin Sign In", JOptionPane.OK_CANCEL_OPTION);
    }

    private void showProfilePopup() {
        if (currentUser == null) return;
        
        JPanel profilePanel = new JPanel(new GridBagLayout());
        profilePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        JLabel profilePicLabel;
        ImageIcon profileIcon = new ImageIcon("ClickBite_Images/profile_pic.png");
        if (profileIcon.getIconWidth() > 0) {
            profilePicLabel = new JLabel(new ImageIcon(profileIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        } else {
            profilePicLabel = new JLabel("👤", JLabel.CENTER);
            profilePicLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        }
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.VERTICAL;
        profilePanel.add(profilePicLabel, gbc);
        
        gbc.gridheight = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel personalInfoLabel = new JLabel("Personal Information");
        personalInfoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        profilePanel.add(personalInfoLabel, gbc);
        
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        profilePanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 2;
        profilePanel.add(new JLabel(currentUser.get("fullName")), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        profilePanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 2;
        profilePanel.add(new JLabel(currentUser.get("email")), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 3;
        profilePanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 2;
        profilePanel.add(new JLabel(currentUser.get("username")), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        JLabel addressLabel = new JLabel("Address");
        addressLabel.setFont(new Font("Arial", Font.BOLD, 16));
        profilePanel.add(addressLabel, gbc);
        
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        profilePanel.add(new JLabel("Street:"), gbc);
        gbc.gridx = 2;
        profilePanel.add(new JLabel(currentUser.get("street")), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 6;
        profilePanel.add(new JLabel("City:"), gbc);
        gbc.gridx = 2;
        profilePanel.add(new JLabel(currentUser.get("city")), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 7;
        profilePanel.add(new JLabel("Postal Code:"), gbc);
        gbc.gridx = 2;
        profilePanel.add(new JLabel(currentUser.get("postal")), gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton editButton = new JButton("Edit Profile");
        editButton.setBackground(new Color(255, 56, 56));
        editButton.setForeground(Color.WHITE);
        editButton.setFocusPainted(false);
        editButton.addActionListener(e -> showEditProfileForm());
        profilePanel.add(editButton, gbc);
        
        JDialog profileDialog = new JDialog(this, "My Profile", true);
        profileDialog.setContentPane(profilePanel);
        profileDialog.pack();
        profileDialog.setLocationRelativeTo(this);
        profileDialog.setVisible(true);
    }

    private void showEditProfileForm() {
        if (currentUser == null) return;
        
        JPanel panel = new JPanel(new GridLayout(8, 2));
        
        JTextField fullNameField = new JTextField(currentUser.get("fullName"));
        JTextField emailField = new JTextField(currentUser.get("email"));
        JTextField usernameField = new JTextField(currentUser.get("username"));
        JTextField streetField = new JTextField(currentUser.get("street"));
        JTextField cityField = new JTextField(currentUser.get("city"));
        JTextField postalField = new JTextField(currentUser.get("postal"));
        JPasswordField passwordField = new JPasswordField();
        passwordField.setText(currentUser.get("password"));
        
        panel.add(new JLabel("Full name:"));
        panel.add(fullNameField);
        panel.add(new JLabel("Email Address:"));
        panel.add(emailField);
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Street Address:"));
        panel.add(streetField);
        panel.add(new JLabel("City:"));
        panel.add(cityField);
        panel.add(new JLabel("Postal/ZIP Code:"));
        panel.add(postalField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        
        int result = JOptionPane.showConfirmDialog(this, panel, "Edit Profile", JOptionPane.OK_CANCEL_OPTION);
        
        if (result == JOptionPane.OK_OPTION) {
            currentUser.put("fullName", fullNameField.getText());
            currentUser.put("email", emailField.getText());
            currentUser.put("username", usernameField.getText());
            currentUser.put("street", streetField.getText());
            currentUser.put("city", cityField.getText());
            currentUser.put("postal", postalField.getText());
            currentUser.put("password", new String(passwordField.getPassword()));
            
            if (!emailField.getText().equals(currentUser.get("email"))) {
                usersDatabase.remove(currentUser.get("email"));
                usersDatabase.put(emailField.getText(), currentUser);
            }
            
            JOptionPane.showMessageDialog(this, "Profile updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private JTextField[] createFields(JPanel panel, String... labels) {
        JTextField[] fields = new JTextField[labels.length];
        for (int i = 0; i < labels.length; i++) {
            panel.add(new JLabel(labels[i] + ":"));
            fields[i] = new JTextField();
            panel.add(fields[i]);
        }
        return fields;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ClickBiteGUI::new);
    }
}
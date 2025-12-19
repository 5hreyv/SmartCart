# 🛒 SmartCart

SmartCart is a mini Android shopping application built to demonstrate clean UI design, solid cart handling, and precise business logic implementation.  
The app focuses on correctness, clarity, and a smooth user experience rather than backend complexity.

---

## ✨ Overview

SmartCart allows users to browse products, add them to a cart, apply coupon rules, and complete checkout with real-time calculations and visual feedback.

All data is handled locally in memory, keeping the app lightweight and easy to understand.

---

## 🚀 Features

### 🛍 Product Listing
- Displays **5+ products** stored in memory
- Each product shows:
  - Product image
  - Original price
  - Tax group (**5% or 18%**)
- Some products are **pre-discounted** and clearly indicated in the UI
- Simple and intuitive **Add to Cart** action

---

### 🛒 Cart Management
- Add multiple products to the cart
- Increase or decrease quantity using plus and minus controls
- All prices and taxes update **in real time**

---

### 🎟 Coupon Logic
- Coupon rules implemented exactly as specified:
  - Minimum cart value: **₹1000**
  - Discount: **20%**
  - Maximum discount: **₹300**
- Coupon **does not apply** to already discounted products
- Coupon button remains disabled until eligibility conditions are met
- Clear UI feedback explains coupon availability

---

### 📊 Cart Summary
- Subtotal calculation
- Tax breakdown based on product tax groups
- Coupon discount amount
- Final payable total

---

### ✅ Checkout Experience
- Checkout confirmation enhanced with **Lottie animation**
- Cart is cleared after successful checkout
- Empty cart state is handled gracefully with visual feedback

---

## 🧠 Technical Highlights
- In-memory product data (no backend)
- Clean separation of UI and business logic
- Real-time calculations handled through ViewModel
- Modular and readable code structure
- Focus on correctness and maintainability

---

## 🛠 Tech Stack
- **Language:** Java  
- **Architecture:** MVVM  
- **UI Components:** RecyclerView, Material Components  
- **State Management:** ViewModel  
- **Animations:** Lottie  

---

## 📁 Project Setup
1. Clone the repository
   ```bash
   git clone https://github.com/your-username/your-repo-name.git
   ```
2. Open the project in Android Studio
3. Build and run the app on an emulator or physical device

## Author

**Shreya Katare**

SmartCart was built with a focus on clean logic, predictable behavior, and a modern Android user experience.

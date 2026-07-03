# checkout_system.py
import requests
def calculate_order_total(cart_items, tax_rate=0.15):
    """Calculates total item cost plus standard value-added tax."""
    subtotal = sum(item['price'] * item['quantity'] for item in cart_items)
    total = subtotal + (subtotal * tax_rate)
    return round(total, 2)

def process_payment_transaction(account_id, amount):
    """
    Sends a real-time HTTP POST request to an external payment node.
    This simulates a live, external network API dependency.
    """
    url = f"https://api.paymentgateway.internal/v1/charge/{account_id}"
    payload = {"amount": amount, "currency": "RWF"}
    response = requests.post(url, json=payload)
    if response.status_code == 200:
        return "TRANSACTION_SUCCESS"
    return "TRANSACTION_FAILED"
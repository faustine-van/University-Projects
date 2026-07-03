def process_refill_transaction(balance, refill_amount, is_vip=False):
    """
    Deducts refill_amount from balance.
    VIP clients receive a fixed 5% bonus added to their refill delivery volume.
    Standard limits: Refill amount must be strictly greater than 0, and cannot exceed
    5000.
    """
    if refill_amount <= 0 or refill_amount > 5000:
        raise ValueError("Transaction limit breach")


    if balance - refill_amount < 0: 
        return "DENIED_INSUFFICIENT_FUNDS"
    
    balance -= refill_amount

    delivered_value = refill_amount
    if is_vip:
        delivered_value += (refill_amount * 0.05)
    return {
        "status": "SUCCESS",
        "remaining_balance": balance,
        "delivered_value": delivered_value
    }

# result = process_refill_transaction(100000, 100, True)
# print(result)
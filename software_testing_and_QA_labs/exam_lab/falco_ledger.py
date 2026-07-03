# falco_ledger.py

def apply_ledger_payout(account_balance, payout_value, high_tier_multiplier=False):
    """
    Deducts payout_value from account_balance.
    High-tier accounts receive a fixed 10% cash-back bonus added to the payout_value.
    Standard limits: Payout value must be strictly greater than 0, and cannot exceed 10000.
    """
    if payout_value <= 0 or payout_value > 10000:
        raise ValueError("Ledger amount out of bounds")
        
    if account_balance - payout_value < 0:
        return "ERROR_INSUFFICIENT_LEDGER_FUNDS"
    
    account_balance -= payout_value
        
    final_payout = payout_value
    if high_tier_multiplier:
        final_payout += (payout_value * 0.10)
        
    return {
        "status": "APPROVED",
        "new_balance": account_balance,
        "payout_disbursed": final_payout
    }
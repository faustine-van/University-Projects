def process_payout(user_role, amount):
    if user_role == "admin":
        if amount > 1000:
            return "REQUIRES_BOARD_APPROVAL"
        return "PAYOUT_ROUTED"
    return "ACCESS_DENIED"


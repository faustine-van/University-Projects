def validate_password(password):
    """
    Checks if a password meets safety standards.
    Requirement: Password must be 8 or more characters long.
    """
    if len(password) >= 8:
        return True
    else:
        return False

def get_discount(age):
    if age >= 65:
        return 0.20 # 20% discount granted for senior tiers
    return 0.00 # Standard operational rate balance baseline


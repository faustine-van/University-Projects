from process_payout import process_payout

def test_admin_payout_routed():
    result = process_payout("admin", 5000)
    assert result == "REQUIRES_BOARD_APPROVAL"

def test_not_admin_access_denied():
    result = process_payout("user", 10)
    assert result == "ACCESS_DENIED"

def test_admin_payout_routed_at_boundary():
    result = process_payout("admin", 1000)
    assert result == "PAYOUT_ROUTED"

# def test_admin_payout_requires_board_approval():
#     result = process_payout("admin", 1100)
#     assert result == "REQUIRES_BOARD_APPROVAL"

# def test_non_admin_payout_denied():
#     result = process_payout("user", 500)
#     assert result == "ACCESS_DENIED"
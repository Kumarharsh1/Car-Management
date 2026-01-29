# Example: Optimize parking fee using dynamic programming
def min_cost(hours, rates):
    dp = [float('inf')] * (hours + 1)
    dp[0] = 0
    for h in range(1, hours + 1):
        for r in rates:
            if h - r[0] >= 0:
                dp[h] = min(dp[h], dp[h - r[0]] + r[1])
    return dp[hours]

if __name__ == "__main__":
    rates = [(1, 20), (3, 50)]  # (hours, cost)
    print("Min cost for 5 hours:", min_cost(5, rates))
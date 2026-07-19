# LICENSE_SYNCHRONIZATION.md

## Purpose

This document describes the business rules for synchronizing the local
Intuition license with the state reported by Google Play Billing.

The document describes **business logic**, not implementation details.

------------------------------------------------------------------------

# Core Concepts

The application stores two license values:

-   **license** -- the current operational license used by the
    application.
-   **lastConfirmedLicense** -- the last license that has been fully
    confirmed by Google Play.

All application modules work **only** with `license`.

`lastConfirmedLicense` is used only by the Billing subsystem.

------------------------------------------------------------------------

# Bootstrap

On first application initialization:

    license = FREE
    lastConfirmedLicense = FREE

After bootstrap both values are identical.

------------------------------------------------------------------------

# Purchase Flow

Purchase Flow may temporarily change `license`.

Example:

    license = PREMIUM
    lastConfirmedLicense = FREE

This temporary state exists only while purchase finalization is in
progress.

## Successful purchase

    setLicense(newLicense)

    ↓

    acknowledgePurchase()

    ↓

    SUCCESS

    ↓

    setLastConfirmedLicense(newLicense)

Result:

    license == lastConfirmedLicense

## Failed acknowledge

If acknowledge fails after all retry attempts:

    rollback:

    license = lastConfirmedLicense

The confirmed license never changes.

------------------------------------------------------------------------

# Startup License Synchronization

Synchronization starts after:

    BillingClient.connect()

    ↓

    onBillingSetupFinished(OK)

------------------------------------------------------------------------

## Step 1

Execute

    queryPurchasesAsync(INAPP)

------------------------------------------------------------------------

## Step 2

If request itself fails:

-   retry using the same retry mechanism as Purchase Flow;
-   if retries are exhausted:
    -   change nothing;
    -   keep current local state.

------------------------------------------------------------------------

## Step 3

If purchase list is empty:

    license = FREE
    lastConfirmedLicense = FREE

Synchronization completed.

------------------------------------------------------------------------

## Step 4

If purchases exist:

Process **every purchase**.

Synchronization result is determined **only after the whole list has
been analyzed**.

------------------------------------------------------------------------

# Product Processing Rules

Current application version recognizes only one Google Play product:

    PREMIUM

All other products are ignored.

Unknown products:

-   are ignored;
-   do not change any license;
-   do not stop synchronization.

------------------------------------------------------------------------

# Purchase State Rules

## PURCHASED + acknowledged == true

    setLicense(PREMIUM)

    ↓

    setLastConfirmedLicense(PREMIUM)

------------------------------------------------------------------------

## PURCHASED + acknowledged == false

    setLicense(PREMIUM)

    ↓

    acknowledgePurchase()

    ↓

    SUCCESS
        ↓
    setLastConfirmedLicense(PREMIUM)

    FAILURE
        ↓
    rollback:
    license = lastConfirmedLicense

Retry policy is identical to Purchase Flow.

------------------------------------------------------------------------

## PENDING

Do nothing.

Google is responsible for finishing or cancelling pending transactions.

Local licenses remain unchanged.

------------------------------------------------------------------------

## UNSPECIFIED_STATE

Do nothing.

Optional logging is allowed.

Local licenses remain unchanged.

------------------------------------------------------------------------

# Rollback Rule

Rollback always returns to the last confirmed license.

Current implementation:

    FREE
    PREMIUM

Future implementation may include:

    FREE
    PREMIUM
    GOLD
    PLATINUM
    ...

Business rule never changes:

    license = lastConfirmedLicense

------------------------------------------------------------------------

# Invariants

## Invariant 1

Outside an active Purchase Flow:

    license == lastConfirmedLicense

------------------------------------------------------------------------

## Invariant 2

`lastConfirmedLicense` may change only after:

1.  Google Play confirms the purchase.
2.  acknowledgePurchase() completes successfully.

------------------------------------------------------------------------

# Design Principles

-   Google Play is the source of truth.
-   `license` is the operational state.
-   `lastConfirmedLicense` is the confirmed state.
-   Unknown products are ignored.
-   Pending purchases never change local licenses.
-   Rollback always restores the last confirmed license.
-   Startup synchronization reconciles local state with Google Play.

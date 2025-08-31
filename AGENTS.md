\# AGENTS.md — Review Guide for Codex



\## How to build \& test

\- Build: `./mvnw -q -DskipTests=false verify` (JDK 17+)

\- Profile dev, DB: MSSQL (Testcontainers if needed)

\- Lint: Checkstyle + SpotBugs; run `./mvnw -q checkstyle:check spotbugs:check`



\## What to enforce

\- \*\*Security\*\*: validate request DTOs (Bean Validation), never trust input; parameterized queries only; no raw string concat in SQL; mask secrets in logs.

\- \*\*JPA\*\*: avoid N+1; use `fetch = LAZY`; transactional boundaries on service layer; pagination for list endpoints.

\- \*\*REST\*\*: nouns, plural resources; proper status codes; error body with code/message; no leaking stack traces.

\- \*\*Null-safety\*\*: avoid auto-unboxing NPE (`Long` vs `long`), `Optional` only in return types (not fields/params).

\- \*\*Tests\*\*: unit tests for services, web layer tests for controllers; cover happy \& edge cases.



\## Ask Codex to

\- Leave inline comments with concrete diffs.

\- Propose patches for Checkstyle/SpotBugs failures.

\- Flag any public endpoints lacking validation or auth.




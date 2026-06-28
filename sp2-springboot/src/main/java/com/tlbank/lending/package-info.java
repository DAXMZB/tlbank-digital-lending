package com.tlbank.lending;

/**
 * TLBank Digital Lending Platform root package.
 * <p>
 * Hexagonal architecture layers:
 * <ul>
 *   <li>{@code presentation} – web and REST adapters</li>
 *   <li>{@code application} – use cases and DTOs</li>
 *   <li>{@code domain} – aggregates, value objects, repository ports</li>
 *   <li>{@code infrastructure} – JPA, cache, notification adapters</li>
 *   <li>{@code security} – authentication and authorization</li>
 *   <li>{@code common} – shared utilities, exceptions, configuration</li>
 * </ul>
 */

# Spinzy — Truth or Dare (Jetpack Compose)

Ye file project ka **build plan + progress tracker** hai. Agar quota khatam ho jaye,
naye session mein Claude ko bolo:

> "Spin-the-bottle-android project ki PROGRESS.md parho aur agla pending step shuru karo."

## Product redesign (summary)
- **Brand:** Spinzy — dark "midnight party" look, neon aurora background (animated), glass cards.
- **Colors:** Truth = Aqua `#2EF2C9`, Dare = Hot Coral `#FF4F7B`, Accent = Sunflower `#FFC94D`, BG = `#0B0A1A`.
- **Core loop:** Players add karo → Vibe pack chuno → Bottle ko finger se **fling** karo (real physics) →
  jis player par ruke woh Truth/Dare chune → 3D flip card → Done / Skip / Chicken-out → points.
- **New features (original app se zyada):**
  - Fling-to-spin physics + haptic "ticks" jab bottle har player ke paas se guzre
  - Players circle mein avatar bubbles (emoji + color)
  - 5 Vibe packs: Chill, Party, Spicy, Couples, Family + "Heat" meter
  - Scoring (Truth +1, Dare +2), har player ke 3 skips, "Chicken out" penalty
  - Dare countdown timer ring
  - Fair mode: same player do baar lagatar nahi aata, sawal repeat nahi hote
  - Custom questions (apne sawal add karo, saved rehte hain)
  - Live scoreboard + end-game podium with confetti
  - Settings saved (DataStore): haptics, fair mode, timer seconds

## Steps
| # | Step | Status |
|---|------|--------|
| 1 | Gradle deps, package rename (`com.umair.spinzy`), manifest, edge-to-edge, theme | ✅ done |
| 2 | Design system: colors, typography, AuroraBackground, GlassCard, GlowButton, Bottle (Canvas) | ✅ done |
| 3 | Data layer: models, PromptBank (packs), DataStore prefs, GameViewModel | ✅ done |
| 4 | Navigation shell (AnimatedContent) + Home screen | ✅ done |
| 5 | Setup screen: players (add/remove, emoji, colors) + pack picker | ✅ done |
| 6 | Game screen: player circle + fling-to-spin bottle + haptics | ✅ done |
| 7 | Truth/Dare chooser + 3D flip prompt card + timer + scoring | ✅ done |
| 8 | Scoreboard sheet, Results podium + confetti | ✅ done |
| 9 | Custom questions screen + Settings | ✅ done |
| 10 | App icon, compile check (debug + release R8 build pass) | ✅ done |
| 11 | Visual QA (Robolectric screenshots) — issues list below | ✅ done |

### Phase 2 — Polish + new features
| # | Step | Status |
|---|------|--------|
| 12 | Theme v2: Outfit font, opaque prompt card, game arena layout (names outside, bigger arena), spotlight beam | ✅ done |
| 13 | Smoothness: no per-frame recomposition (home shimmer, flip), bubbles animate only when needed, bottle settle wobble | ✅ done |
| 14 | 🃏 Wild Cards: random twists (Double points, Everyone does it, Pass it, Mirror, Shield) | ✅ done |
| 15 | ⚖️ Group Verdict for truths (Believe +1 / Busted 🤥) + 🔥 Streaks (3 in a row = bonus) | ✅ done |
| 16 | 🌡️ Heat Escalation mode (game gets spicier every round) | ✅ done |
| 17 | 🍾 Bottle skins (Neon, Champagne, Cola, Potion) + 📳 Shake-to-spin | ✅ done |
| 18 | 🔊 Synthesized sound FX (tick, whoosh, reveal, win) + settings toggles | ✅ done |
| 19 | Re-screenshot, compile debug+release, update notes | ✅ done |
| 20 | Onboarding + Play Store prep (signing, privacy policy, store screenshots) | ⬜ later |

QA issues found in step 11: prompt card was see-through (game showed behind text); bottle cap overlapped top player's
name; arena left big empty gap at bottom; home title shimmer recomposed every frame.

### Phase 3 — "Velvet" redesign + party hub (user reference: plum/wine + peach squircles, serif type, NO emojis)
| # | Step | Status |
|---|------|--------|
| 21 | Theme v3 "Velvet": plum/wine palette, Aleo serif font, squircle shapes, VelvetBackground (glow + rings) | ✅ done |
| 22 | Icon system: remove ALL emojis → Material Rounded vector icons (avatars, packs, wild cards, buttons, prompts) | ✅ done |
| 23 | Party Hub home screen: all games as tiles (Truth or Dare, Coin Flip, Rock Paper Scissors, Dice) | ✅ done |
| 24 | Coin Flip: 3D toss animation, custom labels for both sides (saved), history + tally | ✅ done |
| 25 | Rock Paper Scissors: vs Phone + 2-player pass-and-play, countdown "shoot" animation, best-of score | ✅ done |
| 26 | Dice roller (1–2 dice, tumble animation) | ✅ done |
| 27 | Restyle every existing screen to Velvet (setup, game, prompt card, results, settings, custom) | ✅ done |
| 28 | Verify: build debug+release, screenshots, update notes | ✅ done |

### Phase 4 — Launch-ready: splash, languages, onboarding, feedback, tests
Languages (12): English, Español, Português, Français, Deutsch, Italiano, Русский, Türkçe, العربية (RTL), اردو (RTL), हिन्दी, Bahasa Indonesia.
Every language has translated UI **and its own question bank** (culturally adapted).
| # | Step | Status |
|---|------|--------|
| 29 | Splash: system SplashScreen API + animated in-app intro; velvet app icon | ✅ done |
| 30 | i18n engine (`i18n/` Kotlin string sets, RTL), language picker screen (first launch + settings) | ✅ done |
| 31 | Onboarding: 4 animated pages (pager, parallax, morphing indicator), skip / replay from settings | ✅ done |
| 32 | Feedback system: animated toasts (+Undo), confirm dialogs, input shake validation, rate-us dialog, send feedback | ✅ done |
| 33 | Move every UI string into i18n (English base) | ✅ done |
| 34 | Translate UI: es, pt, fr, de, it, ru, tr, ar, ur, hi, id | ✅ done |
| 35 | Translate question banks for all 11 languages | ✅ done |
| 36 | Tests: game-logic unit tests, translation completeness test, screenshots (LTR+RTL) | ✅ done |
| 37 | Final polish + release build check + notes | ✅ done |

### Phase 5 — Spin the Wheel
| # | Step | Status |
|---|------|--------|
| 38 | Wheel data: items (label, weight %, color), modes (remove winner, custom %), DataStore, WheelViewModel | ✅ done |
| 39 | Wheel canvas: colorful slices, curved labels, blinking rim lights, flapping pointer, fling physics, ticks | ✅ done |
| 40 | Item editor: animated add/remove (slices morph), % sliders + stacked total bar, split equally, presets, undo | ✅ done |
| 41 | Winner reward popup: rays, confetti, badge, chance %, OK removes item (elimination), put back all | ✅ done |
| 42 | Hub tile, 12-language strings, tests, screenshots, build | ✅ done |

### Phase 6 — Truth or Dare UX redesign
Problems found: setup was a long scroll (packs hidden in a side-scroller, big player rows); game screen had
too many competing elements (score strip, hint, button, small bottle); unclear "who was picked" moment;
prompt card had 4 equal-weight buttons.
| # | Step | Status |
|---|------|--------|
| 43 | Setup v2: player chips (FlowRow, pop-in), vibe grid (all packs visible), sticky CTA with summary | ✅ done |
| 44 | Game v2: bigger bottle + arena, tap-or-swipe bottle, idle gesture hint, scores as avatar-stack button | ✅ done |
| 45 | Pick moment: name burst spotlight + chooser bottom sheet | ✅ done |
| 46 | Card v2: swipe right = done / left = skip (stamps, fly-off), one primary action, truth verdict as step 2 | ✅ done |
| 47 | Strings (12 languages), screenshots, tests, build | ✅ done |

### Phase 7 — Big question banks + no-scroll home
Decision (user): English 500+ questions per category (250 truths + 250 dares), other 11 languages 100+ per category.
Storage: extra questions live in raw-string blocks (`data/prompts/more/*.kt`, one question per line) to avoid the JVM
"method too large" limit; `PromptSet + PromptSet` merges them.
| # | Step | Status |
|---|------|--------|
| 48 | Home screen fits one screen (weighted layout, no scroll) | ✅ done |
| 49 | Bank plumbing: raw-string blocks + merge + tests (EN ≥250/250, others ≥50/50 per pack) | ✅ done |
| 50 | English: CHILL, PARTY, SPICY, COUPLES, FAMILY to 250 truths + 250 dares each | ✅ done |
| 51 | es, pt, fr, de, it, ru, tr, ar, ur, hi, id to 50 + 50 per pack | ✅ pending |
| 52 | Tests, build, APK | ⬜ pending |

## Notes / log
- Base: Android Studio template (AGP 9.2.1, Kotlin 2.2.10, Compose BOM 2026.02.01, compileSdk 36.1).
- 2026-11-09: Steps 1–10 complete. `./gradlew assembleDebug` and `assembleRelease` both build with zero warnings.
- 2026-11-09 (phase 2): Steps 12–19 done. New: Outfit font, Wild Cards, Group Verdict, streaks, Heat Rising,
  bottle skins (Neon/Gold/Cola/Potion), shake-to-spin, synthesized SFX (`audio/SoundFx.kt`), score pops,
  spotlight beam, settle wobble, blur behind cards. Debug + release build pass; screenshots reviewed.
- 2026-11-09 (phase 3): Velvet redesign done — Aleo serif + Outfit labels, squircle shapes, zero emojis (all Material
  Rounded vector icons via `ui/components/AppIcons.kt`), Party Hub home, Coin Flip (custom sides), Rock Paper Scissors
  (vs phone / 2 players, first to 3), Dice (1–2). Debug + release build pass; all screens screenshot-reviewed.
- Steps 34/35: all 11 translations (UI + question banks) done and wired in LanguageRegistry. AR + UR banks are family-friendly; couples packs written for married couples.
- 2026-11-09 (phase 4): Steps 29–37 done. Splash (system + animated), language picker (12 languages, RTL for
  Arabic/Urdu), 4-page onboarding, toasts with Undo, confirm dialogs, rating dialog, input shake, send feedback.
  Tests: GameLogicTest (10) + TranslationCompletenessTest (4) all pass; debug + release builds pass with no warnings.
  Run tests: `./gradlew :app:testDebugUnitTest`. A debug APK is at build/Spinzy-debug.apk.
- 2026-11-09 (phase 5): Spin the Wheel done — wheel/WheelViewModel.kt (items, % odds, elimination, DataStore),
  ui/components/WheelCanvas.kt (morphing slices, rim lights, flapping pointer), ui/screens/WheelScreen.kt
  (editor, presets, odds bar, winner popup). WheelLogicTest (7) passes; all 21 unit tests green; release build OK.
- 2026-11-09 (phase 6): Truth or Dare UX redesign — SetupScreen (chips + vibe grid + sticky summary CTA),
  GameScreen (tap/swipe bottle, gesture hint, scores button, pick burst, chooser bottom sheet),
  PromptOverlay (swipe card done/skip with stamps, truth verdict as 2nd step). 21 tests green, release OK.
- Fix (Samsung 360dp): game arena geometry is now responsive — bubble size, ring radius, spin pad and bottle are all
  derived from the arena so the bottle/pad never overlap players; arena uses near full width; top bar no longer wraps.
- Before Play Store: set FEEDBACK_EMAIL in ui/SpinzyApp.kt, add a release signing config, write a privacy policy.
- Next suggested: step 20 (onboarding + Play Store prep), more questions per pack, Urdu language option.
- Delete `build/src-snapshot.tgz` anytime (sirf compile check ke liye bana tha; `build/` gitignored hai).

## File map
```
app/src/main/java/com/umair/spinzy/
├── MainActivity.kt          (installs system splash)
├── i18n/      Strings.kt (English base) + Strings{Es,Pt,Fr,De,It,Ru,Tr,Ar,Ur,Hi,Id}.kt · Language.kt
├── audio/     SoundFx.kt (synthesized WAV → SoundPool)
├── data/      Models.kt · PromptBank.kt · SpinzyPrefs.kt (DataStore) · prompts/Prompts{En,Es,…}.kt
├── wheel/     WheelViewModel.kt (Spin the Wheel state + weighted pick)
├── game/      GameViewModel.kt (state, scoring, validation, undo) · UiEvent.kt (toasts/feedback)
└── ui/
    ├── SpinzyApp.kt          (screen transitions + aurora mood color)
    ├── theme/                Color · Type · Theme
    ├── components/           AuroraBackground · Glass · NeonBottle · Feedback (toast/dialog/rating/shake) · AppIcons · Shapes
    │                         Avatar · TopBar · GlassTextField · Motion (enterUp) · Confetti
    └── screens/              Splash · Language · Onboarding · Home (Party Hub) · Setup · Game · PromptOverlay · ScoreboardSheet
                              Results · CustomPrompts · Settings · Coin · Rps · Dice
```


### Session note (Phase 7 paused)
- Done: English 500+ per category; ES, PT, FR, DE, IT, RU, TR, AR, UR, HI = 100+ per category (MoreXx.kt in data/prompts/more).
- Done: Indonesian (ID) 100+ per category; test min restored to 50.
- TODO Step 52: Gradle build + unit tests + check home screen fits without scroll on small phones.

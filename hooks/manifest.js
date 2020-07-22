module.exports = function(ctx) {
    var fs = ctx.require('fs'),
        path = ctx.require('path');
    var platformPath = path.join(ctx.opts.projectRoot, 'platforms/android');
    var manifestPath = path.join(platformPath, 'AndroidManifest.xml');
    if (!fs.existsSync(manifestPath)) {
        manifestPath = path.join(platformPath, 'app/src/main/AndroidManifest.xml');
    }
    var oldManifest = fs.readFileSync(manifestPath, 'utf8');
    var newManifest = oldManifest.replace(/<action\s+android:name="android.intent.action.MAIN"\s*\/>/g, '')
        .replace(/<category\s+android:name="android.intent.category.LAUNCHER"\s*\/>/g, '');
    fs.writeFileSync(manifestPath, newManifest, 'utf-8');
};